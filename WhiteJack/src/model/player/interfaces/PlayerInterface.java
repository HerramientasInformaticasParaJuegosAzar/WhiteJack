/*
 * Copyright (C) 2014 Viktor Jacynycz García
 *                    Iván Martin Herrero
 *                    Daniel Lago Aguado
 *                    Daniel Arnao Rodríguez
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
package model.player.interfaces;

import model.board.classes.Board;
import model.enums.Decision;

/**
 * 
 * @author Viktor
 */
public interface PlayerInterface {

    /**
     * Main method for a player class to make a decision
     * based on the board
     * @param board the current board
     * @return a possible decision of the enum Decision
     */
    public Decision makeDecision(Board board);
}
