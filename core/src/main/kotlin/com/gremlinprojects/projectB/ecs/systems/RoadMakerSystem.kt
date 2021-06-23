package com.gremlinprojects.projectB.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.assets.AssetManager
import com.gremlinprojects.projectB.assets.ExplorationAtlasAssets
import com.gremlinprojects.projectB.assets.get
import com.gremlinprojects.projectB.ecs.components.NodeComponent
import com.gremlinprojects.projectB.ecs.components.RenderComponent
import com.gremlinprojects.projectB.ecs.components.tags.TagSeenByPlayer
import ktx.ashley.allOf

class RoadMakerSystem(
    private val assetManager: AssetManager
) : IteratingSystem(
    allOf(TagSeenByPlayer::class, NodeComponent::class, RenderComponent::class).get()
) {
    private val roadTexture = assetManager[ExplorationAtlasAssets.Game].findRegion("PLACEHOLDER_road")

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        TODO("Not yet implemented")
    }
}