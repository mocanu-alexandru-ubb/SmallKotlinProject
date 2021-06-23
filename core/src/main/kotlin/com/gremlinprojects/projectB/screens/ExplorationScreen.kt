package com.gremlinprojects.projectB.screens

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.gremlinprojects.projectB.assets.ExplorationAtlasAssets
import com.gremlinprojects.projectB.assets.load
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.ecs.components.tags.TagHasPlayer
import com.gremlinprojects.projectB.ecs.components.tags.TagPlayer
import com.gremlinprojects.projectB.ecs.listeners.HasPlayerListener
import com.gremlinprojects.projectB.ecs.systems.MoveToSystem
import com.gremlinprojects.projectB.ecs.systems.RenderSystem
import com.gremlinprojects.projectB.ecs.systems.RequestPlayerSystem
import com.gremlinprojects.projectB.ecs.systems.TouchHandlerSystem
import com.gremlinprojects.projectB.serialization.EntityReader
import ktx.app.KtxScreen
import ktx.ashley.allOf

fun Sprite.textureAt(texture: TextureRegion, x: Float, y: Float ) {
    this.setRegion(texture)
    this.setBounds(x, y, texture.regionWidth.toFloat(), texture.regionWidth.toFloat())
}

class ExplorationScreen(
    private val batch: Batch,
    private val assetManager: AssetManager,
    private val camera: OrthographicCamera,
    private val engine: PooledEngine
) : KtxScreen {

    override fun show() {
        engine.addSystem(RenderSystem(batch, camera))

        assetManager.load(ExplorationAtlasAssets.Game)
        assetManager.finishLoading()

        val reader = EntityReader(engine, assetManager)
        reader.addEntitiesToEngine()

        val playerEntity = engine.getEntitiesFor(allOf(TagPlayer::class).get())[0]

        engine.apply {
            addSystem(TouchHandlerSystem(camera))
            addSystem(RequestPlayerSystem())
            addSystem(MoveToSystem())
            addSystem(RenderSystem(batch, camera))
        }

        engine.addEntityListener(
            allOf(TagHasPlayer::class, RenderComponent::class).get(),
            HasPlayerListener(playerEntity, engine)
        )
    }

    override fun render(delta: Float) {
        engine.update(delta)
    }
}