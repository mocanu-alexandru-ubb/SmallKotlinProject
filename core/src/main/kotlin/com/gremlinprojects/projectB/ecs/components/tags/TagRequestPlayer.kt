package com.gremlinprojects.projectB.ecs.components.tags

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class TagRequestPlayer : Component {
    companion object {
        val mapper = mapperFor<TagRequestPlayer>()
    }
}