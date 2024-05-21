# Native Push Vapid

## Overview

This project is a Kotlin-based command-line tool for managing VAPID (Voluntary Application Server Identification) keys, which are used in Web Push protocols. The tool allows users to generate new VAPID keys, read existing keys from a file, and output the keys in various encoded formats suitable for use in web and mobile applications.
## Installation

1. **Download the jar from the Releases**:
   ```bash
   wget https://github.com/svenopdehipt/native_push_vapid/releases/latest/download/native-push-vapid.jar
   ```

2. **Run the application**:
   ```bash
   java -jar native-push-vapid.jar [options]
   ```

## Usage

The application is run from the command line with specific arguments to dictate its behavior. The arguments determine whether the keys are read from a file, output in encoded formats, or both.

### Command-Line Arguments

The application accepts a combination of the following arguments:

- `f`: Specifies that the VAPID keys should be read from a file. If the file does not exist, new keys will be generated and saved to this file.
- `e`: Outputs the VAPID keys in base64-url-encoded format.
- `s`: Outputs the base64-encoded server key, which is used in Flutter applications when web support is enabled.

### Example Commands

1. **Generate new keys and save to a file**:
   ```bash
   java -jar vapid-keys-management.jar f
   ```

2. **Generate new keys, save to a file, and output the keys in encoded formats**:
   ```bash
   java -jar vapid-keys-management.jar fe
   ```

3. **Generate new keys and output the server key**:
   ```bash
   java -jar vapid-keys-management.jar s
   ```

### Detailed Steps

1. **Run the application**:
   ```bash
   java -jar vapid-keys-management.jar [arguments]
   ```

2. **Specify the path for the VAPID keys file** when prompted:
   - Default path: `vapid_keys`
   - You can provide a custom path or press Enter to use the default.

3. **View the encoded keys** if the `e` or `s` arguments are specified:
   - For `e`, the application will output the public and private keys in base64-url-encoded format.
   - For `s`, the application will output the base64-encoded server key.

## Related projects

- [Native Push](https://github.com/Native-Push/native_push): The main Flutter library
- [Native Push Client](https://github.com/Native-Push/native_push_client): The Flutter library to be used with `Native Push Server`
- [Native Push Server](https://github.com/Native-Push/native_push_server): The server which can be used as a microservices and a
  library which can be used for an own server.
- [Native Push Notification Service](https://github.com/Native-Push/native_push_notification_service): Library which should be used to show images on notifications send with
  `Native Push` to an iOS system

## Contributing

Contributions are welcome! Please submit a pull request or open an issue to discuss changes.

## License

This project is licensed under the BSD-3 License - see the [LICENSE](LICENSE.md) file for details.
