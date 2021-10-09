/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.characteristic;

import com.oop.model.Hunter;

public interface Damaged {

    void beAttacked(int hp);

    void attackedBy(Hunter hunter);

}
