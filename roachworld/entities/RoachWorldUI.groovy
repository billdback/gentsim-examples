/*
Copyright © 2009 William D. Back
This file is part of gentsim-examples.

    gentsim-examples is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    gentsim-examples is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with gentsim-examples.  If not, see <http://www.gnu.org/licenses/>.
*/
import org.gentsim.framework.EntityDescription

rwui  = new EntityDescription("ui")

rwui.handleTimeUpdate { time ->
  println "time: ${time}"
}

rwui.handleEntityCreated("roach") { roach ->
  println "roach ${roach.id} created at ${roach.location}"
}

rwui.handleEntityStateChanged ("roach", "location") { roach ->
  println "roach ${roach.id} moved to ${roach.location}"
}

rwui.handleEntityStateChanged ("roach", "state") { roach ->
  println "roach ${roach.id} changed state to ${roach.state}"
}

rwui.handleEntityDestroyed ("roach") { roach ->
  println "roach ${roach.id} is dead"
}

rwui.handleEntityCreated("dropped-food") { food ->
  println "${food.amount} food was dropped at ${food.location}"
}

rwui.handleEntityDestroyed("dropped-food") { food ->
  println "${food.id} food is gone"
}

