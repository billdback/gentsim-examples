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
import org.gentsim.framework.*
import org.apache.log4j.xml.DOMConfigurator;

import org.gentsim.util.Trace

// Is this needed?  Only if log4j.xml is not on the classpath.
DOMConfigurator.configure("log4j.xml")

// create the simulation and specify location of entities, etc.
beehive = new TimeSteppedSimulation(["entities", "events", "services"])

// Set up tracing.
Trace.off "statistics"
//Trace.on "entities"
//Trace.on "events"


beehive.newService("outside_temp")
beehive.newEntity("hive")
beehive.newEntity("thermometer")
(0..10).each { beehive.newEntity("bee") }

// Start the simulation.
beehive.run(10)

