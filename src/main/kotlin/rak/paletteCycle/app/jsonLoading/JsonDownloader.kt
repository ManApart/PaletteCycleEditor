package rak.paletteCycle.app.jsonLoading

import rak.pixellwp.cycling.jsonLoading.JsonDownloadListener
import rak.pixellwp.cycling.jsonModels.ImageInfo
import java.net.URL


class JsonDownloader(private val image: ImageInfo, private val listener: JsonDownloadListener) {
    private val baseUrl = "http://www.effectgames.com/demos/canvascycle/image.php?file="
    private val logTag = "JsonDownloader"
    fun doInBackground(vararg params: String?): String {
        return downloadImage()
    }

    private fun downloadImage(): String {
        var json = ""
        try {
            val inputStream = URL(baseUrl + image.id).openStream()

            val s = java.util.Scanner(inputStream).useDelimiter("\\A")
            json = if (s.hasNext()) s.next() else ""

            inputStream.close()
            return json
        } catch (e: Exception) {
//            Log.e(logTag, "Unable to download image from ${baseUrl + image.id}")
            e.printStackTrace()
        }
        return json
    }

    fun onPostExecute(result: String?) {
        val json = cleanJson(result)
        val jsonSample = if (json.length > 100) "${json.substring(0, 100)} ... ${json.substring(json.length - 100)}" else json
//        Log.d(logTag, "downloaded json for ${image.name} from ${baseUrl + image.id} to ${image.fileName}: $jsonSample")
        listener.downloadComplete(image, json)
    }

    private fun cleanJson(json: String?): String {
        if (json == null) return ""
        if (json.length > 25) {
            val start = json.indexOf("{filename")
            return json.substring(start, json.length - 4)
        }
        return json
    }

}