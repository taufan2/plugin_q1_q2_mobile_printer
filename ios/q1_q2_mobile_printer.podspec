#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint q1_q2_mobile_printer.podspec' to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'q1_q2_mobile_printer'
  s.version          = '0.0.1'
  s.summary          = 'Flutter Plugin for Q1 Q2 device'
  s.description      = <<-DESC
Flutter Plugin for Q1 Q2 device
                       DESC
  s.homepage         = 'http://example.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Your Company' => 'email@example.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'
  s.platform = :ios, '8.0'

  # Flutter.framework does not contain a i386 slice. Only x86_64 simulators are supported.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'VALID_ARCHS[sdk=iphonesimulator*]' => 'x86_64' }
end
