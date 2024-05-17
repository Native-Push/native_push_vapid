import com.interaso.webpush.VapidKeys
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.io.path.Path
import kotlin.system.exitProcess

@OptIn(ExperimentalEncodingApi::class)
fun main(args: Array<String>) {
    val arg = args.firstOrNull()
    if (arg.isNullOrEmpty() || !(arg.contains('f') || arg.contains('e') || arg.contains('s'))) {
        println("No valid option specified. Please specify an combination of f (file), e (encoded) or s (server key).")
        println("File can be used to specify an file, where the vapid keys will be stored. " +
                "If the file already exists and contain valid vapid keys, the keys will be read from the file.")
        println("Encoded can used to output the base-url-encoded private and public key to the console.")
        println("Server key can be used to output the base64-encoded server key, which must be used in Flutter, " +
                "if web is a supported platform of your app.")
        exitProcess(1)
    }
    val keys = if (arg.contains('f')) {
        print("Please enter the path for the vapid_keys file [vapid_keys]: ")
        var vapidKeysFile = readlnOrNull()
        if (vapidKeysFile.isNullOrBlank()) {
            vapidKeysFile = "vapid_keys"
        }
        VapidKeys.load(Path(vapidKeysFile))
    }
    else {
        VapidKeys.generate()
    }

    if (arg.contains('e')) {
        println("Public Key: ${keys.x509PublicKey}")
        println("Private Key: ${keys.pkcs8PrivateKey}")
    }
    if (arg.contains('s')) {
        println("Server Key: ${Base64.encode(keys.applicationServerKey)}")
    }
}