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
import org.gentsim.util.*
import org.gentsim.random.UniformRandom

// this must be run from the current directory to work.
def rwsim = new Simulation(["events", "entities", "services"], true) 
//rwsim.manuallyStep()

rwsim.useJMS("tcp://localhost:61616")

// Set up tracing.
Trace.on "statistics"
Trace.off "entities"
Trace.off "events"
Trace.off "debug"

// Create the individual services and things.
def kitchen = rwsim.newService("kitchen")
rwsim.newEntity("messy-person")
rwsim.newEntity("ui")
rwsim.newEntity("roach-world-analyzer")

// Create some random roaches to start with.
def widthRandom = new UniformRandom(0..kitchen.width)
def lengthRandom = new UniformRandom(0..kitchen.length)
(1..2000).each {
  def x = widthRandom.nextLong()
  def y = lengthRandom.nextLong()
  def r = rwsim.newEntity("roach", ["location" : [x, y]])
}

// start the simulation
rwsim.paused = true
rwsim.run(10)
