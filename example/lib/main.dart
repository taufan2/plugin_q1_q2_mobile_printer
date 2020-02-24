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
            ],
          ),
        ),
      ),
    );
  }
}
