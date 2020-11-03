/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.ComprasDao;
import Dao.ItensCompraDao;
import Dao.ItensEntradaDao;
import Modelo.Compras;
import Modelo.ItensCompra;
import Modelo.ItensEntrada;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suporte T.I 2
 */
public class TelaCompras2 extends javax.swing.JFrame {

    /**
     * Creates new form TelaDespesa
     */
    String DataConvertidaISO;
    String DataConvertidaBR;
    String ValorConvertido;
    String ValorItemConvertido;
    String DataISO;
    Double TotalItem;
    int Cod_Item;

    public TelaCompras2() {
        initComponents();
        MostrarDespesa();
    }

    public void ConverterDataBR() {
        //Pega nesse formato 00/00/0000 e converte para esse  formato 0000-00-00
        String dia = TxtDataInicial.getText().substring(0, 2);
        String mes = TxtDataInicial.getText().substring(3, 5);
        String ano = TxtDataInicial.getText().substring(6, 10);
        DataConvertidaISO = ano + "-" + mes + "-" + dia;
    }

    public void ConverterDataISO() {
        //Pega nesse formato 0000-00-00 e converte para esse  formato 00/00/0000
        String dia = DataISO.substring(8, 10);
        String mes = DataISO.substring(5, 7);
        String ano = DataISO.substring(0, 4);
        DataConvertidaBR = "" + dia + "" + mes + "" + ano;
    }
    //carrega Tabela

    public void MostrarDespesa() {
        JTDespesa.getColumnModel().getColumn(0).setPreferredWidth(30);
        JTDespesa.getColumnModel().getColumn(1).setPreferredWidth(100);
        JTDespesa.getColumnModel().getColumn(2).setPreferredWidth(30);
        JTDespesa.getColumnModel().getColumn(3).setPreferredWidth(50);
        DefaultTableModel modelo = (DefaultTableModel) JTDespesa.getModel();
        modelo.setNumRows(0);
        ComprasDao cdao = new ComprasDao();

        cdao.BuscarDespesa().forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_compra(),
                c.getDescricao_compra(),
                c.getValor_compra(),
                c.getData_compra()
            });
        });
    }
//carrega Tabela

    public void MostrarItens() {
        JTItensCompra.getColumnModel().getColumn(0).setPreferredWidth(60);
        JTItensCompra.getColumnModel().getColumn(1).setPreferredWidth(100);
        JTItensCompra.getColumnModel().getColumn(2).setPreferredWidth(70);
        JTItensCompra.getColumnModel().getColumn(2).setPreferredWidth(70);
        DefaultTableModel modelo = (DefaultTableModel) JTItensCompra.getModel();
        modelo.setNumRows(0);
        ItensCompraDao cdao = new ItensCompraDao();

        cdao.BuscarItensCompra(Integer.parseInt(TxtCod.getText())).forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_item_compra(),
                c.getDescricao_item_compra(),
                c.getQuantidade(),
                c.getValor_item_compra(),
                c.getValor_total_item_compra()
            });
        });
    }

    //Salvar Item da Venda
    public void SalvarItemCompra() {
        ItensCompra i = new ItensCompra();
        ItensCompraDao idao = new ItensCompraDao();
        if (TxtDescItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Descrição do item é obrigatório!");
            TxtDescItem.requestFocus();
        } else if (TxtValorItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Valor de Venda do item é obrigatório!");
            TxtValorItem.requestFocus();
        } else if (TxtQuantidadeItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Quantidade do item é obrigatório!");
            TxtQuantidadeItem.requestFocus();
        }

        i.setDescricao_item_compra(TxtDescItem.getText());
        i.setCod_compra(Integer.parseInt(TxtCod.getText()));
        i.setQuantidade(Integer.parseInt(TxtQuantidadeItem.getText()));
        i.setValor_item_compra(Double.parseDouble(TxtValorItem.getText()));
        i.setValor_total_item_compra(Double.parseDouble(LbValorTotalItem.getText()));
        idao.SalvarItem(i);
        LimparCamposItens();
        MostrarItens();
    }

    //Remover Item
    public void RemoverItem() {
        Cod_Item = Integer.parseInt(JTItensCompra.getValueAt(JTItensCompra.getSelectedRow(), 0).toString());

        if (Cod_Item == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Item!!");

        } else {
            ItensCompraDao idao = new ItensCompraDao();
            idao.RemoverItensCompra(Cod_Item);
            MostrarItens();
        }
    }

    public void SalvarCompra() {
        Compras d = new Compras();
        ComprasDao ddao = new ComprasDao();

        if (TxtDescricao.getText() == null || TxtDescricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Descrição é um campo obrigatório!");
            TxtDescricao.requestFocus();
        } else if (TxtDataInicial.getText() == null || TxtDescricao.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data Inicial é um campo obrigatório!");
            TxtDataInicial.requestFocus();
        } else if (TxtValor.getText() == null || TxtValor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Valor é um campo obrigatório!");
            TxtValor.requestFocus();
        } else if (TxtCodDinheiro.getText() == null || TxtCodDinheiro.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Quantidade de meses é um campo obrigatório!");
            TxtCodDinheiro.requestFocus();
        } else {

            //Faz troca do , pelo .
            ValorConvertido = String.valueOf(TxtValor.getText());
            ValorConvertido = ValorConvertido.replaceAll(",", ".");

            ConverterDataBR();
            d.setDescricao_compra(TxtDescricao.getText());
            d.setData_compra(DataConvertidaISO);
            d.setValor_compra(Double.parseDouble(ValorConvertido));
            d.setCod_dinheiro_utilizado(Integer.parseInt(TxtCodDinheiro.getText()));
            ddao.SalvarDespesa(d);
            MostrarDespesa();
            LimparCampos();

        }
    }

    public void LimparCamposItens() {
        TxtDescItem.setText("");
        TxtValorItem.setText("");
        TxtQuantidadeItem.setText("");
        LbValorTotalItem.setText("");
    }

    public void LimparCampos() {
        TxtCod.setText("");
        TxtDescricao.setText("");
        TxtDataInicial.setText("");
        TxtCodDinheiro.setText("");
        TxtValor.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtCod = new javax.swing.JTextField();
        TxtDescricao = new javax.swing.JTextField();
        TxtValor = new javax.swing.JTextField();
        TxtDataInicial = new javax.swing.JFormattedTextField();
        TxtCodDinheiro = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Salvar = new javax.swing.JButton();
        Salvar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        TxtPesquisa = new javax.swing.JTextField();
        ButtonValor = new javax.swing.JRadioButton();
        ButtonDescricao = new javax.swing.JRadioButton();
        ButtonCodigo = new javax.swing.JRadioButton();
        ButtonData = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTDespesa = new javax.swing.JTable();
        JCDinheiroUtilizado = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TxtDescItem = new javax.swing.JTextField();
        TxtValorItem = new javax.swing.JTextField();
        TxtQuantidadeItem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        LbValorTotalItem = new javax.swing.JLabel();
        jBRemoverItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTItensCompra = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Compras");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel2.setText("Código");

        jLabel3.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel3.setText("Descrição");

        jLabel4.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel4.setText("Data Compra");

        jLabel5.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel5.setText("Dinheiro Utilizado");

        TxtCod.setEditable(false);
        TxtCod.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        TxtDescricao.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        TxtValor.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        try {
            TxtDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        TxtDataInicial.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        TxtCodDinheiro.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 18))); // NOI18N

        Salvar.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        Salvar.setText("Salvar");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        Salvar1.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        Salvar1.setText("Pesquisar");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salvar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Salvar1)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros para Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 12))); // NOI18N

        TxtPesquisa.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        ButtonValor.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(ButtonValor);
        ButtonValor.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonValor.setText("Valor");

        ButtonDescricao.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(ButtonDescricao);
        ButtonDescricao.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonDescricao.setText("Descrição");

        ButtonCodigo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(ButtonCodigo);
        ButtonCodigo.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonCodigo.setText("Código");

        ButtonData.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(ButtonData);
        ButtonData.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonData.setText("Data");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCodigo)
                    .addComponent(ButtonDescricao)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonValor)
                    .addComponent(ButtonData)))
        );

        jLabel6.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel6.setText("Valor da Compra");

        JTDespesa.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        JTDespesa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Data", "Valor"
            }
        ));
        JTDespesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTDespesaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTDespesa);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens da venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel7.setText("Descrição");

        jLabel9.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel9.setText("Valor Item");

        jLabel10.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel10.setText("Quantidade");

        TxtValorItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtValorItemKeyReleased(evt);
            }
        });

        TxtQuantidadeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtQuantidadeItemActionPerformed(evt);
            }
        });
        TxtQuantidadeItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtQuantidadeItemKeyReleased(evt);
            }
        });

        jButton1.setText("Inserir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel16.setText("Valor Total");

        LbValorTotalItem.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        LbValorTotalItem.setForeground(new java.awt.Color(0, 102, 255));

        jBRemoverItem.setText("Remover");
        jBRemoverItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRemoverItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addComponent(TxtDescItem, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(20, 20, 20))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(24, 24, 24)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(LbValorTotalItem, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(TxtValorItem, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                .addComponent(TxtQuantidadeItem)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jBRemoverItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtDescItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TxtValorItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtQuantidadeItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(LbValorTotalItem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBRemoverItem)))
                .addContainerGap())
        );

        JTItensCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Qtd", "Valor Unt", "Valor Total"
            }
        ));
        JTItensCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JTItensCompra.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(JTItensCompra);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDescricao))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCodDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCDinheiroUtilizado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(TxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TxtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(TxtCodDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCDinheiroUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(TxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        SalvarCompra();        // TODO add your handling code here:
    }//GEN-LAST:event_SalvarActionPerformed

    private void JTDespesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTDespesaMouseClicked

        if (JTDespesa.getSelectedRow() != -1) {
            TxtCod.setText(JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 0).toString());
            TxtDescricao.setText(JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 1).toString());
            //Faz a troca . por ,
            ValorConvertido = (JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 2).toString());
            ValorConvertido = ValorConvertido.replaceAll("\\.", ",");
            TxtValor.setText(ValorConvertido);

            DataISO = JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 3).toString();
            ConverterDataISO();
            TxtDataInicial.setText(DataConvertidaBR);
            MostrarItens();
        }
    }//GEN-LAST:event_JTDespesaMouseClicked

    private void TxtValorItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtValorItemKeyReleased
        ValorItemConvertido = String.valueOf(TxtValorItem.getText());
        ValorItemConvertido = ValorItemConvertido.replaceAll(",", ".");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);


    }//GEN-LAST:event_TxtValorItemKeyReleased

    private void TxtQuantidadeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQuantidadeItemActionPerformed

    }//GEN-LAST:event_TxtQuantidadeItemActionPerformed

    private void TxtQuantidadeItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtQuantidadeItemKeyReleased

        //Coloca no padrão de reais somente 2 casas após a vírgula
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        TotalItem = (Double.parseDouble(TxtValorItem.getText()) * (Integer.parseInt(TxtQuantidadeItem.getText())));
        LbValorTotalItem.setText((Double.toString(TotalItem)));
    }//GEN-LAST:event_TxtQuantidadeItemKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (TxtCod.getText() == null || TxtCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Compra!!");
        } else {
            SalvarItemCompra();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBRemoverItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRemoverItemActionPerformed
        RemoverItem();        
    }//GEN-LAST:event_jBRemoverItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCompras2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCompras2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCompras2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCompras2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCompras2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ButtonCodigo;
    private javax.swing.JRadioButton ButtonData;
    private javax.swing.JRadioButton ButtonDescricao;
    private javax.swing.JRadioButton ButtonValor;
    private javax.swing.JComboBox<Object> JCDinheiroUtilizado;
    private javax.swing.JTable JTDespesa;
    private javax.swing.JTable JTItensCompra;
    private javax.swing.JLabel LbValorTotalItem;
    private javax.swing.JButton Salvar;
    private javax.swing.JButton Salvar1;
    private javax.swing.JTextField TxtCod;
    private javax.swing.JTextField TxtCodDinheiro;
    private javax.swing.JFormattedTextField TxtDataInicial;
    private javax.swing.JTextField TxtDescItem;
    private javax.swing.JTextField TxtDescricao;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtQuantidadeItem;
    private javax.swing.JTextField TxtValor;
    private javax.swing.JTextField TxtValorItem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBRemoverItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
