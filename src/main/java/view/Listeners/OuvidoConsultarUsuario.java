package view.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import view.CadastrarUsuarios;

/**
 *
 * @author joseg github.com/JoseGabrielNF
 */
public class OuvidoConsultarUsuario implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton botao = ((JButton) e.getSource());
            CadastrarUsuarios janelacad = new CadastrarUsuarios(botao.getName());
            janelacad.setVisible(true);
        }
    }

}
