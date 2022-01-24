package cs.hse.rezunik.cosmicnews

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}