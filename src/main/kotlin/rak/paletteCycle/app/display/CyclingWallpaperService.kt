package rak.paletteCycle.app.display

import rak.paletteCycle.app.jsonLoading.ImageLoader
import rak.pixellwp.cycling.jsonLoading.ImageLoadedListener
import rak.pixellwp.cycling.jsonModels.ImageInfo


class CyclingWallpaperService {
    private val logTag = "CyclingWallpaperService"
    private lateinit var imageLoader: ImageLoader


    inner class CyclingWallpaperEngine : ImageLoadedListener {
        init {
            imageLoader.addLoadListener(this)
        }

        private val defaultImage = ImageInfo("DefaultImage", "DefaultImage", 0)
        private var currentImage = defaultImage

        private fun changeCollection(collectionName: String) {
            val image = imageLoader.getImageInfoForCollection(collectionName)
            changeImage(image)
        }

        private fun changeImage(image: ImageInfo) {
            if (image != currentImage) {
//                Log.d(logTag, "Changing from ${currentImage.name} to ${image.name}.")
                if (imageLoader.imageIsReady(image)) {
                    currentImage = image
//                    drawRunner.image = imageLoader.loadImage(image)

                } else {
                    imageLoader.downloadImage(image)
                }
            }
        }

        override fun imageLoadComplete(image: ImageInfo) {
            changeImage(image)
        }
    }
}