/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author majemase
 */
public class modeloCargo {

    public static Cargo buscarCargoStr(String cargoStr) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.name().equalsIgnoreCase(cargoStr)) {
                return cargo;
            }
        }
        return null;
    }
}
