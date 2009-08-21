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

bee = new EntityDescription("bee")

bee.flapping = false // indicates that the bee is flapping its wings

// create parameters for all entities of this type
bee.parameter "desired_temp", 75.0 // desired temperature in degrees farenheight

// whenever the temp changes, see if this bee should start flapping.
bee.handleEntityStateChanged ("hive", "temperature") { hive ->
  flapping = (hive.temperature > desired_temp) ? true : false
}

