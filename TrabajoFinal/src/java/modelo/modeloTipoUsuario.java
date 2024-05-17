/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author majemase
 */
public class modeloTipoUsuario {

    public static TipoUsuario buscarTipoUsuStr(String tipoUsuStr) {
        for (TipoUsuario tipoUsu : TipoUsuario.values()) {
            if (tipoUsu.name().equalsIgnoreCase(tipoUsuStr)) {
                return tipoUsu;
            }
        }
        return null;
    }
}
