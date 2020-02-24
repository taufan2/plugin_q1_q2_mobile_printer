
# Plugin Q1 Q2 
  
Ini adalah plugin flutter untuk Mobile Printer Q1 Q2, plugin ini masih dalam tahap pengembangan.  
  
## Perangkat yang sudah dicoba  
  
 - [x] Q1 Q2 Android Mobile Printer 
 
## Instalasi
Tambahkan ini fi file pubspec.yaml

```yaml
dependencies:
  flutter:
    sdk: flutter
    
  plugin_sunmi_v2:
    git: git://github.com/taufan2/flutter-sunmi.git
```
  
## Penggunaan  
  
### 1. Import Plugin  
  
```dart  
import 'package:q1_q2_mobile_printer/q1_q2_mobile_printer.dart';
```  
  
  
### 2. Instasiasi Class `Q1Q2MobilePrinter` lalu `.bind()`  
```dart  
 Q1Q2MobilePrinter _printer = Q1Q2MobilePrinter();  
  
 _printer.bind(); // ini harus dilakukan  
```  
  
### 3. Mulai Print  
  
```dart  
 try {    
  await _printer.start(); // ini harus dilakukan sebelum memasukan method lainnya
  await _printer.printText("Lorem ipsum dolor sit amet.");
  await _printer.commit(); //  ini harus dilakukan untuk melakukan print
 } catch (e) {
  await _printer.cancel();
 }  
```  
  
#### Method Tersedia  

Semuanya masih dalam tahap pengembangan, belum seluruh fitur tersedia.

---    
```dart  
 Future<void> sendRaw(Uint8List bytes);  
```  
---  
```dart  
 Future<void> printText(String text);  
```  
---  
```dart  
 Future<void> lineFeed({int lines = 1});  
```  
---  
  
```dart  
 Future<void> printColumn(List<String> text, {Int32List columnWidth, Int32List columnAlignment});  
```  
---  
```dart  
 Future<void> setAlignment({int position: ALIGNMENT_LEFT})  
```  
---  
```dart  
 Future<void> setFontSize({double fontSize = 14})  
```  
---  
```dart  
 Future<void> setBoldFont({bool bold = true})  
```  
---  
```dart  
 Future<void> setFontType({String fontType: FONT_TYPE_A})
```
---  
```dart  
 Future<void> setEmphasized({bool emphasized: false})
```
---  
```dart  
 Future<void> commit()
```  
---  
```dart  
 Future<void> start() 
```  
---  
```dart  
 Future<void> cancel()
```