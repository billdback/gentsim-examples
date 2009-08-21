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

droppedFood = new EntityDescription("dropped-food")

// Remaining amount of food in energy points.
droppedFood.amount   = 0
droppedFood.location = [0, 0]

droppedFood.method ("takeBite") { biteSize ->
  def actualBite = Math.min(biteSize, amount)
  amount = amount - actualBite
  if (amount <= 0) destroy()
  actualBite
}

