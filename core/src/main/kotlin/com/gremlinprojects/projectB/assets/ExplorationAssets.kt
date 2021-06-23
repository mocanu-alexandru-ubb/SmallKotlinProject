package com.gremlinprojects.projectB.assets

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.assets.getAsset
import ktx.assets.load

enum class ExplorationAtlasAssets(val path: String) {
    Game("images/game.atlas")
}

inline fun AssetManager.load(asset: ExplorationAtlasAssets) = load<TextureAtlas>(asset.path)
inline operator fun AssetManager.get(asset: ExplorationAtlasAssets) = getAsset<TextureAtlas>(asset.path)