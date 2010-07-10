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

// A messy person drops food at random. 
person = new EntityDescription("messy-person")

person.usesService "kitchen"
person.avg_drop_frequency = 0.8 // how often, on average as percent, the person drops some food.
person.avg_drop_size      = 50  // how big, on average, the dropped food is.

person.parameter "random", { new Random() }

person.handleTimeUpdate { time ->
  // See if this is one of those times that food is dropped.
  if (Math.abs(random.nextFloat()) < avg_drop_frequency) {
    def x = Math.abs(random.nextInt() % kitchen.width)
    def y = Math.abs(random.nextInt() % kitchen.length)
    def amt = Math.round(Math.abs(random.nextGaussian() * avg_drop_size))
    newEntity("dropped-food", ["location": [x, y], "amount": amt])
  }

}

