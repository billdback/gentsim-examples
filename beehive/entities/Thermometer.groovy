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

import groovy.swing.SwingBuilder

thermometer = new EntityDescription("thermometer")
thermometer.parameter "time", 0

thermometer.handleTimeUpdate() {t ->
  time = t
}

thermometer.handleEntityStateChanged ("hive", "temperature") { hive ->
  printf "%02d:%02d hive is %2.1f degrees Fahrenheit\n",
         (int)(time / 60) % 24, (int)time % 60, hive.temperature
}

