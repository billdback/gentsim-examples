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
import org.gentsim.util.Trace
import org.gentsim.random.NormalRandom

// create the simulation and specify location of entities, etc.
beehive = new Simulation(["entities", "events", "services"], true)

// Set up tracing.
Trace.off "statistics"
Trace.on "entities"
Trace.on "events"

beehive.newEntity("ui")
beehive.newService("outside_temp")
beehive.newEntity("hive")
//beehive.newEntity("thermometer")

// Create the bees with a normal desired temp around 75 degrees F.
def bee
def rand = new NormalRandom(75, 6)
(0..100).each { 
  bee = beehive.newEntity("bee") 
  // NOTE:  comment to see the impact of all bees flapping.
  bee.desired_temp = rand.nextLong()
}

// Start the simulation.
beehive.cycleLength = 250 // .25 second cycles
beehive.paused = true
beehive.run(100)

