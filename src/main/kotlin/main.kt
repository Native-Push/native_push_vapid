import com.interaso.webpush.VapidKeys
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.io.path.Path
import kotlin.system.exitProcess

@OptIn(ExperimentalEncodingApi::class)
fun main(args: Array<String>) {
    // Get the first argument from the command line arguments
    val arg = args.firstOrNull()

    // Check if the argument is null or doesn't contain any valid options
    if (arg.isNullOrEmpty() || !(arg.contains('f') || arg.contains('e') || arg.contains('s'))) {
        // Print usage instructions
        println("No valid option specified. Please specify an combination of f (file), e (encoded) or s (server key).")
        println("File can be used to specify a file where the VAPID keys will be stored. " +
                "If the file already exists and contains valid VAPID keys, the keys will be read from the file.")
        println("Encoded can be used to output the base64-url-encoded private and public key to the console.")
        println("Server key can be used to output the base64-encoded server key, which must be used in Flutter, " +
                "if web is a supported platform of your app.")
        // Exit the process if no valid option is specified
        exitProcess(1)
    }

    // Determine whether to load keys from a file or generate new ones
    val keys = if (arg.contains('f')) {
        // Prompt the user for the file path
        print("Please enter the path for the vapid_keys file [vapid_keys]: ")
        var vapidKeysFile = readlnOrNull()
        // Use default file name if none is provided
        if (vapidKeysFile.isNullOrBlank()) {
            vapidKeysFile = "vapid_keys"
        }
        // Load the keys from the specified file
        VapidKeys.load(Path(vapidKeysFile))
    }
    else {
        // Generate new VAPID keys if no file option is provided
        VapidKeys.generate()
    }

    // Output the public and private keys in base URL-encoded format if 'e' option is specified
    if (arg.contains('e')) {
        println("Public Key: ${keys.x509PublicKey}")
        println("Private Key: ${keys.pkcs8PrivateKey}")
    }

    // Output the server key in base64-encoded format if 's' option is specified
    if (arg.contains('s')) {
        println("Server Key: ${Base64.encode(keys.applicationServerKey)}")
    }
}
