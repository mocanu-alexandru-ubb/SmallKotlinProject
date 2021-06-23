package com.gremlinprojects.projectB.serialization

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.gremlinprojects.projectB.assets.ExplorationAtlasAssets
import com.gremlinprojects.projectB.assets.get
import com.gremlinprojects.projectB.ecs.components.NodeComponent
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.ecs.components.TouchableComponent
import com.gremlinprojects.projectB.ecs.components.tags.TagHasPlayer
import com.gremlinprojects.projectB.ecs.components.tags.TagPlayer
import com.gremlinprojects.projectB.ecs.components.tags.TagRequestPlayer
import com.gremlinprojects.projectB.screens.textureAt
import ktx.ashley.entity
import ktx.ashley.plusAssign
import ktx.ashley.with

class EntityReader(
    private val engine: PooledEngine,
    assetManager: AssetManager,
) {
    private val player: TextureAtlas.AtlasRegion = assetManager[ExplorationAtlasAssets.Game].findRegion("PLACEHOLDER_player")
    private val emptyNode: TextureAtlas.AtlasRegion = assetManager[ExplorationAtlasAssets.Game].findRegion("NodeEmpty")

    private fun readAllFromFile() {
        engine.entity {
            with<RenderComponent> {
                sprite.textureAt(player, 100f, 100f)
                z = 1
                visible = true
            }
            with<TagPlayer>()
        }

        engine.entity {
            entity.add(NodeComponent(1, neighbors = listOf(2, 30)))
            with<RenderComponent> {
                sprite.setRegion(emptyNode)
                sprite.setAlpha(0.5f)
                sprite.setBounds(100f, 100f, 32f, 32f)
                visible = true
            }
            with<TouchableComponent> {
                onTouch = { println("Touched 1"); it += TagRequestPlayer(); true }
            }
            with<TagHasPlayer>()
        }

        engine.entity {
            entity.add(NodeComponent(2, neighbors = listOf(1)))
            with<RenderComponent> {
                sprite.textureAt(emptyNode, 250f, 300f)
                visible = true
            }
            with<TouchableComponent> {
                onTouch = { println("Touched 2"); it += TagRequestPlayer(); true }
            }
        }

        engine.entity {
            entity.add(NodeComponent(30, listOf(1)))
            with<RenderComponent> {
                sprite.setRegion(emptyNode)
                sprite.setBounds(800f, 200f, 32f, 32f)
                visible = true
            }
            with<TouchableComponent> {
                onTouch = { println("Touched 3"); it += TagRequestPlayer(); true }
            }
        }

    }

    fun addEntitiesToEngine() {
        readAllFromFile()
    }

}