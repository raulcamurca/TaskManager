package controller;

import exceptions.TarefaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tarefa;

public class TarefaFormController {

    @FXML
    private TextField campoTitulo;

    @FXML
    private TextArea campoDescricao;

    @FXML
    private CheckBox checkConcluida;

    private Tarefa tarefa;

    private boolean salvo = false;

    public void setTarefa(Tarefa tarefa) {

        this.tarefa = tarefa;

        if (tarefa != null) {

            campoTitulo.setText(
                    tarefa.getTitulo()
            );

            campoDescricao.setText(
                    tarefa.getDescricao()
            );

            checkConcluida.setSelected(
                    tarefa.isConcluida()
            );
        }
    }

    public boolean isSalvo() {
        return salvo;
    }

    @FXML
    private void salvar() {

        String titulo =
                campoTitulo.getText().trim();

        String descricao =
                campoDescricao.getText().trim();

        if (titulo.isEmpty()) {

            Alert alerta =
                    new Alert(
                            Alert.AlertType.WARNING
                    );

            alerta.setTitle("Validação");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    "Informe o título da tarefa."
            );

            alerta.showAndWait();

            return;
        }

        if (tarefa == null) {

            tarefa = new Tarefa(
                    0,
                    titulo,
                    descricao
            );

        } else {

            tarefa.setTitulo(titulo);

            tarefa.setDescricao(descricao);

            tarefa.setConcluida(
                    checkConcluida.isSelected()
            );
        }

        try {

            tarefa.validar();

        } catch (TarefaException erro) {

            Alert alerta =
                    new Alert(
                            Alert.AlertType.WARNING
                    );

            alerta.setTitle("Validação");
            alerta.setHeaderText(null);
            alerta.setContentText(
                    erro.getMessage()
            );

            alerta.showAndWait();

            return;
        }

        salvo = true;

        fecharJanela();
    }

    @FXML
    private void cancelar() {

        salvo = false;

        fecharJanela();
    }

    private void fecharJanela() {

        Stage stage =
                (Stage) campoTitulo
                        .getScene()
                        .getWindow();

        stage.close();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }
}