/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos.controller;

import grafos.algoritmos.AlgoritmoBFS;
import grafos.algoritmos.AlgoritmoDFS;
import grafos.arquivo.In;
import grafos.model.Desenho;
import grafos.model.Grafo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author john_
 */
public class FXMLVBoxMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem menuItemProcessosExplorarGrafo;
    @FXML
    private AnchorPane anchorPaneGrafo;
    @FXML
    private ComboBox comboBoxGrafos;
    @FXML
    private ComboBox comboBoxAlgoritmos;
    @FXML
    private Button buttonIniciar;
    @FXML
    private Button buttonBuscarGrafo;
    @FXML
    private ListView listViewCodigo;
    @FXML
    private TextField TextFieldVerticeOrigem;
    @FXML
    private TextField TextFieldVerticeDestino;

    private List<String> listAlgoritmos = new ArrayList<String>();
    private List<String> listGrafos = new ArrayList<String>();
    private List<String> listCodigo = new ArrayList<String>();
    private ObservableList<String> observableListAlgoritmos;
    private ObservableList<String> observableListGrafos;
    private ObservableList<String> observableListCodigo;
    public static File arquivoGrafo;

    public static int origem, destino;
    //Group componentesLine = new Group();
    //Group componentesCircle = new Group();
    //Group componentesText = new Group();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        listViewCodigo.setVisible(false);
        //new JMetro(JMetro.Style.LIGHT).applyTheme(buttonIniciar);
        //new JMetro(JMetro.Style.LIGHT).applyTheme(comboBoxAlgoritmos);
        //new JMetro(JMetro.Style.LIGHT).applyTheme(buttonBuscarGrafo);
        //new JMetro(JMetro.Style.LIGHT).applyTheme(TextFieldVerticeOrigem);
        //new JMetro(JMetro.Style.LIGHT).applyTheme(TextFieldVerticeDestino);
        

        TextFieldVerticeOrigem.setOnMousePressed(evt -> {
            TextFieldVerticeOrigem.setText("");
        });
        
        TextFieldVerticeDestino.setOnMousePressed(evt -> {
            TextFieldVerticeDestino.setText("");
        });

        listAlgoritmos.add("DFS");
        listAlgoritmos.add("BFS");
        listAlgoritmos.add("Algoritmo de Kruskal");
        listAlgoritmos.add("Algoritmo de Prim");
        listAlgoritmos.add("Algoritmo de Dijkstra");
        listAlgoritmos.add("Algoritmo de Bellman-Ford");

        observableListAlgoritmos = FXCollections.observableArrayList(listAlgoritmos);
        comboBoxAlgoritmos.setItems(observableListAlgoritmos);

        TextFieldVerticeOrigem.setPromptText("Vértice de Origem");
        TextFieldVerticeDestino.setPromptText("Vértice de Destino");
        TextFieldVerticeOrigem.setText("Vértice de Origem");
        TextFieldVerticeDestino.setText("Vértice de Destino");

        TextFieldVerticeOrigem.setDisable(true);
        TextFieldVerticeDestino.setDisable(true);
        
       // DropShadow ds = new DropShadow();
        //ds.setSpread(0.1);
        //TextFieldVerticeOrigem.setEffect(ds);
       // TextFieldVerticeDestino.setEffect(ds);
        
        //buttonIniciar.setEffect(new InnerShadow());
        //buttonBuscarGrafo.setEffect(new InnerShadow());

    }

    public void teste() {

    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    private void centralizarText(Text text, Circle circle) {
        double W = text.getBoundsInLocal().getWidth();
        double H = text.getBoundsInLocal().getHeight();
        text.relocate(circle.getCenterX() - W / 2, circle.getCenterY() - H / 2);
    }

    @FXML
    public void handleButtonCarregarCodigo() throws IOException, InterruptedException {
        String algoritmo = comboBoxAlgoritmos.getSelectionModel().getSelectedItem().toString();

        if (!algoritmo.equals("DFS") && !algoritmo.equals("BFS")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Algoritmo não implementado!");
            alert.setContentText("Tente outro algoritmo!");
            alert.show();
        } else {

            if (!algoritmo.equals("Algoritmo de Kruskal") && !algoritmo.equals("Algoritmo de Bellman-Ford")) {
                TextFieldVerticeOrigem.setDisable(false);

                if (algoritmo.equals("Algoritmo de Dijkstra") || algoritmo.equals("Algoritmo de Bellman-Ford")) {
                    TextFieldVerticeDestino.setDisable(false);
                } else if (algoritmo.equals("DFS")) {
                    listCodigo = new ArrayList<String>();
                    listCodigo.add("public class AlgoritmoDFSGrafo {");
                    listCodigo.add("    private booblean[] marcado;");
                    listCodigo.add("    private int[] arestaPara;");
                    listCodigo.add("");
                    listCodigo.add("    public AlgoritmoDFSGrafo(Grafo G, int vo){");
                    listCodigo.add("            this.vo = vo;");

                    listCodigo.add("            arestaPara = new int[G.V()];");
                    listCodigo.add("            marcado = new boolean[G.V()];");
                    listCodigo.add("            dfs(G, vo);");
                    listCodigo.add("    }");
                    listCodigo.add("");

                    listCodigo.add("    private void dfs(Grafo G, int v) {");
                    listCodigo.add("            marcado[v] = true;");
                    listCodigo.add("            for (Aresta a : G.adj(v)) {");
                    listCodigo.add("                    int x = a.getV2();");
                    listCodigo.add("                    if (!marcado[x]) {");
                    listCodigo.add("                            arestaPara[x] = v;");
                    listCodigo.add("                            dfs(G, x);");
                    listCodigo.add("                    }");
                    listCodigo.add("            }");
                    listCodigo.add("    }");
                    listCodigo.add("}");

                    observableListCodigo = FXCollections.observableArrayList(listCodigo);
                    listViewCodigo.setItems(observableListCodigo);
                    listViewCodigo.setStyle("-fx-font-size: 11pt;");
                } else if (algoritmo.equals("BFS")) {
                    listCodigo = new ArrayList<String>();
                    listCodigo.add("public class AlgoritmoBFSGrafo {");
                    listCodigo.add("    private boolean[] marcado;");
                    listCodigo.add("    private int[] arestaPara;");
                    listCodigo.add("    private int[] distPara;");
                    listCodigo.add("    private int v;");
                    listCodigo.add("");
                    listCodigo.add("    public AlgoritmoBFSGrafo(Grafo G, int v){");
                    listCodigo.add("        this.v = v;");
                    listCodigo.add("        arestaPara = new int[G.V()];");
                    listCodigo.add("        marcado = new boolean[G.V()];");
                    listCodigo.add("       distPara = new Integer[G.V()];");
                    listCodigo.add("       bfs(G, v);");
                    listCodigo.add("   }");
                    listCodigo.add("");

                    listCodigo.add("    private void dfs(Grafo G, int v) {");
                    listCodigo.add("        Fila<Integer> f = new Fila<Integer>();");
                    listCodigo.add("        f.enfileira(vo);");
                    listCodigo.add("        marcado[vo] = true;");
                    listCodigo.add("        distPara[vo] = 0;");
                    listCodigo.add("        while (!f.isEmpty()) {");
                    listCodigo.add("            int v = f.desenfileira();");
                    listCodigo.add("            for (Aresta a : G.adj(v)) {");
                    listCodigo.add("                int x = a.getV2();}");
                    listCodigo.add("                if (!marcado[x]) {");
                    listCodigo.add("                    arestaPara[x] = v;}");
                    listCodigo.add("                    distPara[x] = distPara[v] + 1;");
                    listCodigo.add("                    marcado[x] = true;");
                    listCodigo.add("                    f.enfileira(x);");
                    listCodigo.add("                }");
                    listCodigo.add("            }");
                    listCodigo.add("        }");
                    listCodigo.add("    }");
                    listCodigo.add("}");
                    observableListCodigo = FXCollections.observableArrayList(listCodigo);
                    listViewCodigo.setItems(observableListCodigo);
                    listViewCodigo.setStyle("-fx-font-size: 11pt;");
                }
            }
        }
        
        listViewCodigo.setVisible(true);
    }

    @FXML
    public void handleButtonIniciar() throws IOException {
        String algoritmo = comboBoxAlgoritmos.getSelectionModel().getSelectedItem().toString();
        if (arquivoGrafo == null || comboBoxAlgoritmos.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Campos obrigatórios não preenchidos!!");
            alert.setContentText("Verifique se algum campo não foi preechido da forma correta!");
            alert.show();
        } else {
            if (!TextFieldVerticeOrigem.getText().equals("Vértice de Origem") && !TextFieldVerticeDestino.getText().equals("Vértice de Destino")) {
                destino = Integer.parseInt(TextFieldVerticeDestino.getText());
                origem = Integer.parseInt(TextFieldVerticeOrigem.getText());      
            } else if (!TextFieldVerticeOrigem.getText().equals("Vértice de Origem")){
                origem = Integer.parseInt(TextFieldVerticeOrigem.getText());
                System.out.println("DFS ou BFS " + origem);
            } 
                
            
            if (algoritmo.equals("DFS")) {
                
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/grafos/view/FXMLExplorarComDFS.fxml"));
                anchorPane.getChildren().setAll(a);
            } else {
                
                AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/grafos/view/FXMLExplorarComBFS.fxml"));
                anchorPane.getChildren().setAll(a);
            }

        }

        AlgoritmoDFS.velocidade = 1000;
        AlgoritmoBFS.velocidade = 1000;

    }

    @FXML
    public void handleButtonBuscarGrafo() throws IOException {
      
        Group componentesLine = new Group();
        Group componentesCircle = new Group();
        Group componentesText = new Group();
        
        anchorPaneGrafo.getChildren().clear();
        
        Desenho criarGrafo = new Desenho();
        FileChooser leitor = new FileChooser();
        leitor.setTitle("Open Resource File");
        arquivoGrafo = leitor.showOpenDialog(null);
        
        

        Grafo grafoDesenho;
        In in = new In(arquivoGrafo);
        grafoDesenho = new Grafo(in);
        //System.out.print(grafoDesenho.A());
        //System.out.print(grafoDesenho.V());
        //System.out.print(grafoDesenho.getAdj()[0]);
        
        criarGrafo.desenharGrafo(grafoDesenho, anchorPaneGrafo, componentesLine, componentesCircle, componentesText);

    }

}
