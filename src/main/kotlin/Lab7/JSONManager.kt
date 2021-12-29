import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.*

class JSONManager {
    val json = Json {
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
            }
        }
    }

    inline fun <reified T> encodeJSON(objects: T) : String = json.encodeToString(value = objects)
    inline fun <reified T> decodeJSON(textJSON: String) : T = json.decodeFromString(string = textJSON)
}