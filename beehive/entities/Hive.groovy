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

// create the entity.
hive = new EntityDescription("hive")

hive.temperature = 70.0 // temperature of the hive
hive.numberBeesFlapping = 0

hive.usesService("outside_temp")

hive.handleEntityStateChanged ("bee", "flapping") { bee ->
  if (bee.flapping) numberBeesFlapping++
  else              numberBeesFlapping--
}

hive.handleTimeUpdate { time ->
  println "temp is ${temperature} and outside is ${outside_temp.getTemp(time)}"
  // the temperature will increment towards the outside temperature
  if (temperature > outside_temp.getTemp(time)) temperature--
  else                                          temperature++

  // For each bee that is flapping its wings, drop the temperature by .1 degrees
  temperature -= numberBeesFlapping * 0.1
}
