package com.gremlinprojects.projectB

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.gremlinprojects.projectB.screens.ExplorationScreen
import com.gremlinprojects.projectB.utils.config.HEIGHT
import com.gremlinprojects.projectB.utils.config.WIDTH
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.inject.Context
import ktx.inject.register

class Main : KtxGame<KtxScreen>() {
    private val context = Context()

    override fun create() {
        context.register {
            bindSingleton<Batch>(SpriteBatch())
            bindSingleton(AssetManager())
            bindSingleton(OrthographicCamera().apply { setToOrtho(false, WIDTH.toFloat(), HEIGHT.toFloat()) })
            bindSingleton(PooledEngine())
            addScreen(ExplorationScreen(inject(), inject(), inject(), inject()))
        }
        setScreen<ExplorationScreen>()
    }
}