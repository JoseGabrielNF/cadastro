package view.Listeners;

import controller.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author joseg github.com/JoseGabrielNF
 */
public class OuvidoExcluirUsuario implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton botao = ((JButton) e.getSource());
            Controlador.excluir(botao.getName());
            JOptionPane.showMessageDialog(null, "Usuario Excluido");
        }
    }

}

