package GUIs;

import DAOs.*;
import Entidades.*;
import myUtil.*;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

import myUtil.UsarGridBagLayout;

public class GUIProduto extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeNext = new ImageIcon(getClass().getResource("/icones/next.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnNext = new JButton(iconeNext);
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelIdProduto = new JLabel("Id do produto");
    JTextField textFieldIdProduto = new JTextField(20);
    JLabel labelNomeProduto = new JLabel("Nome do produto");
    JTextField textFieldNomeProduto = new JTextField(20);
    JLabel labelQuantidadeEstoqueProduto = new JLabel("Quantidade em estoque do produto");
    JTextField textFieldQuantidadeEstoqueProduto = new JTextField(20);
    JLabel labelTipoProdutoIdTipoProduto = new JLabel("Tipo de produto");
    JTextField textFieldTipoProdutoIdTipoProduto = new JTextField(20);
    JLabel labelFabricanteIdFabricante = new JLabel("Marca do fabricante");
    JTextField textFieldFabricanteIdFabricante = new JTextField(20);
    JLabel labelDataProduto = new JLabel("Data do produto");
    JTextField textFieldDataProduto = new JTextField(20);

//Daos para FK
    DAOTipoProduto daoTipoProduto = new DAOTipoProduto();
    DAOFabricante daoFabricante = new DAOFabricante();

//Entidades para FK
    TipoProduto tipoProduto = new TipoProduto();
    Fabricante Fabricante = new Fabricante();

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOProduto daoProduto = new DAOProduto();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    Produto produto;

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnNext.setEnabled(r);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnNext.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean idProduto, boolean nomeProduto, boolean quantidadeEstoqueProduto, boolean tipoProdutoIdTipoProduto, boolean FabricanteIdFabricante) {
        if (idProduto) {
            textFieldIdProduto.requestFocus();
            textFieldIdProduto.selectAll();
        }
        textFieldIdProduto.setEnabled(idProduto);
        textFieldIdProduto.setEditable(idProduto);
        textFieldNomeProduto.setEditable(nomeProduto);
        textFieldQuantidadeEstoqueProduto.setEditable(quantidadeEstoqueProduto);
        textFieldTipoProdutoIdTipoProduto.setEditable(tipoProdutoIdTipoProduto);
        textFieldFabricanteIdFabricante.setEditable(FabricanteIdFabricante);

    }

    public void zerarAtributos() {
        textFieldNomeProduto.setText("");
        textFieldQuantidadeEstoqueProduto.setText("");
        textFieldTipoProdutoIdTipoProduto.setText("");
        textFieldFabricanteIdFabricante.setText("");
    }
    Color corPadrao = labelIdProduto.getBackground();

    public GUIProduto(Point posicao, Dimension dimensao) {
        setLocationRelativeTo(null);
        setTitle("CRUD - Produto");
        setSize(dimensao);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnNext.setToolTipText("Próximo novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar toolbar1 = new JToolBar();
        toolbar1.add(labelIdProduto);
        toolbar1.add(textFieldIdProduto);
        toolbar1.add(btnRetrieve);
        toolbar1.add(btnCreate);
        toolbar1.add(btnNext);
        toolbar1.add(btnUpdate);
        toolbar1.add(btnDelete);
        toolbar1.add(btnSave);
        toolbar1.add(btnCancel);
        toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel();
        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centro);
        usarGridBagLayout.add(labelNomeProduto, textFieldNomeProduto, corPadrao);
        usarGridBagLayout.add(labelQuantidadeEstoqueProduto, textFieldQuantidadeEstoqueProduto, corPadrao);
        labelQuantidadeEstoqueProduto.setToolTipText("Informe a quantidade em estoque do produto");
        textFieldQuantidadeEstoqueProduto.setToolTipText("Informe a quantidade em estoque do produto");
        usarGridBagLayout.add(labelTipoProdutoIdTipoProduto, textFieldTipoProdutoIdTipoProduto, Color.yellow);
        labelTipoProdutoIdTipoProduto.setToolTipText("Tipo de produto (exemplo: fruta, eletrônico)");
        textFieldTipoProdutoIdTipoProduto.setToolTipText("Tipo de produto (exemplo: fruta, eletrônico)");
        usarGridBagLayout.add(labelFabricanteIdFabricante, textFieldFabricanteIdFabricante, Color.yellow);
        usarGridBagLayout.add(labelDataProduto, textFieldDataProduto, Color.CYAN);
        textFieldDataProduto.setToolTipText("Informe a data no formato: DD/MM/yyyy");
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldIdProduto.requestFocus();
        textFieldIdProduto.selectAll();
        textFieldIdProduto.setBackground(Color.GREEN);
        labelAviso.setText("Digite um IdProduto e clic [Pesquisar]");

//--------------- listeners ----------------- 
        textFieldIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                produto = new Produto();
                textFieldIdProduto.setText(textFieldIdProduto.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdProduto.getText().equals("")) {
                    List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldIdProduto.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldIdProduto.requestFocus();
                            textFieldIdProduto.selectAll();
                        }
                    }

                    textFieldIdProduto.requestFocus();
                    textFieldIdProduto.selectAll();
                } else {
                    try {
                        produto.setIdProduto(Integer.valueOf(textFieldIdProduto.getText()));
                        produto = daoProduto.obter(produto.getIdProduto());
                        if (produto != null) { //se encontrou na lista
                            textFieldNomeProduto.setText(String.valueOf(produto.getNomeProduto()));
                            textFieldQuantidadeEstoqueProduto.setText(String.valueOf(produto.getQuantidadeEstoqueProduto()));
                            textFieldTipoProdutoIdTipoProduto.setText(String.valueOf(produto.getTipoProdutoIdTipoProduto().getIdTipoProduto() + "-" + produto.getTipoProdutoIdTipoProduto().getNomeTipoProduto()));
                            textFieldFabricanteIdFabricante.setText(String.valueOf(produto.getFabricanteIdFabricante().getIdFabricante() + "-" + produto.getFabricanteIdFabricante().getNomeFabricante()));
                            textFieldDataProduto.setText(sdf.format(produto.getDataProduto()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldIdProduto.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldIdProduto.setOpaque(true);
                        textFieldIdProduto.selectAll();
                        textFieldIdProduto.requestFocus();
                        textFieldIdProduto.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true, true, true, true);
                textFieldNomeProduto.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

        // ---------------------- botao next ------------------------------
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int prox = daoProduto.autoIdProduto();
                textFieldIdProduto.setText(String.valueOf(prox));
                btnRetrieve.doClick();
                btnCreate.doClick();
            }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    produto = new Produto();
                }
                try {
                    produto.setIdProduto(Integer.valueOf((textFieldIdProduto.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldIdProduto.setBackground(Color.red);
                }
                produto.setNomeProduto(String.valueOf(textFieldNomeProduto.getText()));
                try {
                    produto.setQuantidadeEstoqueProduto(Integer.valueOf((textFieldQuantidadeEstoqueProduto.getText())));
                } catch (Exception erro4) {
                    deuRuim = true;
                    textFieldQuantidadeEstoqueProduto.setBackground(Color.red);
                }
                try{
                    produto.setDataProduto(sdf.parse(textFieldDataProduto.getText()));
                }catch (Exception erro5) {
                    deuRuim = true;
                    textFieldDataProduto.setBackground(Color.red);
                }
                
                produto.setTipoProdutoIdTipoProduto(daoTipoProduto.obter(Integer.valueOf(textFieldTipoProdutoIdTipoProduto.getText().split("-")[0])));
                produto.setFabricanteIdFabricante(daoFabricante.obter(Integer.valueOf(textFieldFabricanteIdFabricante.getText().split("-")[0])));
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoProduto.inserir(produto);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoProduto.atualizar(produto);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                GUIProdutoListagem guiProdutoListagem = new GUIProdutoListagem(daoProduto.listInOrderNome(), getBounds().x, getBounds().y, dimensao);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + produto.getNomeProduto() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoProduto.remover(produto);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNomeProduto.requestFocus();
                    textFieldNomeProduto.selectAll();
                }
            }
        });// ----------------   Janela Pesquisar para FKs -----------------
        textFieldTipoProdutoIdTipoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoTipoProduto.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldTipoProdutoIdTipoProduto.getBounds().y + textFieldTipoProdutoIdTipoProduto.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldTipoProdutoIdTipoProduto.setText(selectedItem);

                        //preparar para salvar
                        tipoProduto = daoTipoProduto.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldTipoProdutoIdTipoProduto.requestFocus();
                        textFieldTipoProdutoIdTipoProduto.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldFabricanteIdFabricante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoFabricante.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldFabricanteIdFabricante.getBounds().y + textFieldFabricanteIdFabricante.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldFabricanteIdFabricante.setText(selectedItem);

                        //preparar para salvar
                        Fabricante = daoFabricante.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldFabricanteIdFabricante.requestFocus();
                        textFieldFabricanteIdFabricante.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldNomeProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNomeProduto.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNomeProduto.setBackground(corPadrao);
            }
        });
        textFieldQuantidadeEstoqueProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldQuantidadeEstoqueProduto.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldQuantidadeEstoqueProduto.setBackground(corPadrao);
            }
        });
        textFieldTipoProdutoIdTipoProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTipoProdutoIdTipoProduto.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTipoProdutoIdTipoProduto.setBackground(Color.yellow);
            }
        });
        textFieldFabricanteIdFabricante.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldFabricanteIdFabricante.setBackground(Color.orange);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldFabricanteIdFabricante.setBackground(Color.yellow);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });

        pack();
        setModal(true);
        setLocation(posicao);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIProduto(new Point(880, 250), new Dimension(800, 600));
    }
}
