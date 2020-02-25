import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'dart:typed_data';

import 'package:flutter/material.dart';

import 'package:q1_q2_mobile_printer/q1_q2_mobile_printer.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  Q1Q2MobilePrinter _printer = Q1Q2MobilePrinter();

  @override
  void initState() {
    super.initState();
    _printer.bind();
  }

  void goPrint() {
    _printer.printText(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
    _printer.commit();
  }

  void printColumn() async {
    _printer.start();
    _printer.setAlignment(position: Q1Q2MobilePrinter.ALIGNMENT_LEFT);
    _printer.printText('Text');
    _printer.printBlankLine();
    _printer.printText('Text 2');
    _printer.lineFeed();
    _printer.printColumn(['String 1', 'String 2'], columnWidth: Int32List.fromList([15,14]));
    _printer.printColumn(['String 1', 'String 2'], columnWidth: Int32List.fromList([14,14]), isLast: true);
    _printer.commit();
  }

  void printImage() async {
    Directory appDocDir = await getApplicationDocumentsDirectory();
    String appDocPath = appDocDir.path;

    print(appDocPath);

    _printer.printImage("/data/data/taufan.q1_q2_mobile_printer_example/files/242b5565142705cb03cacde4601b7e0f.png");
    _printer.commit();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              RaisedButton(child: Text('Transaction Print'), onPressed: this.goPrint),
              RaisedButton(child: Text('Print Column'), onPressed: this.printColumn),
              RaisedButton(child: Text('Print Image'), onPressed: this.printImage),
            ],
          ),
        ),
      ),
    );
  }
}
