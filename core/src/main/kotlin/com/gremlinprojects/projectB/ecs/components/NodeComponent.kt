package com.gremlinprojects.projectB.ecs.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

private var lastId = 1

class NodeComponent (
    val id: Int = lastId++,
    val neighbors : List<Int> = emptyList()
) : Component {
    var visible = false

    companion object {
        val mapper = mapperFor<NodeComponent>()
    }

}