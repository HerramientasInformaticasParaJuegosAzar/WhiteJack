/*
 * Copyright (C) 2014 usuario_local
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package model.set;

import model.set.card.Card;
import model.set.card.enums.Suit;
import model.set.card.enums.Value;

/**
 *
 * @author usuario_local
 */
public class Set {
    private Card[] cardSet;
    
    public Set(){
        this.cardSet=new Card[52];
        int iterator = 0;
        for(Suit s: Suit.values()){
            for(Value v:Value.values()){
                this.cardSet[iterator]=new Card(v, s);
            }
        }
    }
}
