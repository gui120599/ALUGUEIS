/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.ComprasDao;
import Dao.ItensCompraDao;
import Modelo.Compras;
import Modelo.ItensCompra;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author guilherme.santos
 */
public class TelaCompra extends javax.swing.JFrame {

    /**
     * Creates new form TelaItensCompra
     */
    String DataConvertidaISO;
    String DataConvertidaBR;
    String ValorConvertido;
    String ValorItemConvertido;
    String DataISO;
    Double TotalItem;
    int CodCompra;

    public TelaCompra() {
        initComponents();
        MostrarCompra();
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

    public void MostrarCompra() {
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
            MostrarCompra();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PainelCompras = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtCod = new javax.swing.JTextField();
        TxtDescricao = new javax.swing.JTextField();
        TxtValor = new javax.swing.JTextField();
        TxtDataInicial = new javax.swing.JFormattedTextField();
        TxtCodDinheiro = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        Salvar = new javax.swing.JButton();
        Salvar1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        TxtPesquisa = new javax.swing.JTextField();
        ButtonValor = new javax.swing.JRadioButton();
        ButtonDescricao = new javax.swing.JRadioButton();
        ButtonCodigo = new javax.swing.JRadioButton();
        ButtonData = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTDespesa = new javax.swing.JTable();
        JCDinheiroUtilizado = new javax.swing.JComboBox<>();
        PainelItensCompra = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TxtDescItem5 = new javax.swing.JTextField();
        TxtValorItem5 = new javax.swing.JTextField();
        TxtQuantidadeItem5 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        LbValorTotalItem5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTItensCompra = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Compras");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        PainelCompras.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel3.setText("Código");

        jLabel4.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel4.setText("Descrição");

        jLabel5.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel5.setText("Data Compra");

        jLabel6.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel6.setText("Dinheiro Utilizado");

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

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 18))); // NOI18N

        Salvar.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        Salvar.setText("Salvar");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        Salvar1.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        Salvar1.setText("Pesquisar");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salvar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(Salvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Salvar1)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros para Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 12))); // NOI18N

        TxtPesquisa.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        ButtonValor.setBackground(new java.awt.Color(255, 255, 255));
        ButtonValor.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonValor.setText("Valor");

        ButtonDescricao.setBackground(new java.awt.Color(255, 255, 255));
        ButtonDescricao.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonDescricao.setText("Descrição");

        ButtonCodigo.setBackground(new java.awt.Color(255, 255, 255));
        ButtonCodigo.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonCodigo.setText("Código");

        ButtonData.setBackground(new java.awt.Color(255, 255, 255));
        ButtonData.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        ButtonData.setText("Data");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButtonCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonCodigo)
                    .addComponent(ButtonDescricao)
                    .addComponent(TxtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonValor)
                    .addComponent(ButtonData)))
        );

        jLabel31.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel31.setText("Valor da Compra");

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

        javax.swing.GroupLayout PainelComprasLayout = new javax.swing.GroupLayout(PainelCompras);
        PainelCompras.setLayout(PainelComprasLayout);
        PainelComprasLayout.setHorizontalGroup(
            PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelComprasLayout.createSequentialGroup()
                .addGroup(PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelComprasLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDescricao))
                    .addGroup(PainelComprasLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PainelComprasLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtCodDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JCDinheiroUtilizado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        PainelComprasLayout.setVerticalGroup(
            PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelComprasLayout.createSequentialGroup()
                .addGroup(PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelComprasLayout.createSequentialGroup()
                        .addGroup(PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(TxtCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(TxtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(TxtDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(TxtCodDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCDinheiroUtilizado, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PainelComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(TxtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Compras", PainelCompras);

        PainelItensCompra.setBackground(new java.awt.Color(255, 255, 255));
        PainelItensCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens da venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 14))); // NOI18N
        PainelItensCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PainelItensCompraMouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Calibri", 2, 13)); // NOI18N
        jLabel27.setText("Descrição");

        jLabel28.setFont(new java.awt.Font("Calibri", 2, 13)); // NOI18N
        jLabel28.setText("Valor Item");

        jLabel29.setFont(new java.awt.Font("Calibri", 2, 13)); // NOI18N
        jLabel29.setText("Quantidade");

        TxtValorItem5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtValorItem5KeyReleased(evt);
            }
        });

        TxtQuantidadeItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtQuantidadeItem5ActionPerformed(evt);
            }
        });
        TxtQuantidadeItem5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtQuantidadeItem5KeyReleased(evt);
            }
        });

        jButton6.setText("Inserir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Calibri", 2, 13)); // NOI18N
        jLabel30.setText("Valor Total");

        LbValorTotalItem5.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        LbValorTotalItem5.setForeground(new java.awt.Color(0, 102, 255));

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

        javax.swing.GroupLayout PainelItensCompraLayout = new javax.swing.GroupLayout(PainelItensCompra);
        PainelItensCompra.setLayout(PainelItensCompraLayout);
        PainelItensCompraLayout.setHorizontalGroup(
            PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelItensCompraLayout.createSequentialGroup()
                .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelItensCompraLayout.createSequentialGroup()
                        .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PainelItensCompraLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(LbValorTotalItem5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PainelItensCompraLayout.createSequentialGroup()
                                .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel28))
                                .addGap(20, 20, 20)
                                .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TxtValorItem5, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                    .addComponent(TxtQuantidadeItem5)))
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(36, 36, 36)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelItensCompraLayout.createSequentialGroup()
                        .addComponent(TxtDescItem5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelItensCompraLayout.setVerticalGroup(
            PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelItensCompraLayout.createSequentialGroup()
                .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelItensCompraLayout.createSequentialGroup()
                        .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(TxtDescItem5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(TxtValorItem5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelItensCompraLayout.createSequentialGroup()
                                .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TxtQuantidadeItem5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))
                                .addGap(14, 14, 14)
                                .addGroup(PainelItensCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(LbValorTotalItem5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Itens da Compra", PainelItensCompra);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (TxtCod.getText() == null || TxtCod.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione uma Compra!!");
        } else {
            SalvarItemCompra();
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void TxtQuantidadeItem5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtQuantidadeItem5KeyReleased

        //Coloca no padrão de reais somente 2 casas após a vírgula
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        TotalItem = (Double.parseDouble(TxtValorItem.getText()) * (Integer.parseInt(TxtQuantidadeItem.getText())));
        LbValorTotalItem.setText((Double.toString(TotalItem)));
    }//GEN-LAST:event_TxtQuantidadeItem5KeyReleased

    private void TxtQuantidadeItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQuantidadeItem5ActionPerformed

    }//GEN-LAST:event_TxtQuantidadeItem5ActionPerformed

    private void TxtValorItem5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtValorItem5KeyReleased
        ValorItemConvertido = String.valueOf(TxtValorItem.getText());
        ValorItemConvertido = ValorItemConvertido.replaceAll(",", ".");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
    }//GEN-LAST:event_TxtValorItem5KeyReleased

    private void JTDespesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTDespesaMouseClicked

        if (JTDespesa.getSelectedRow() != -1) {

            TxtCod.setText(JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 0).toString());
            CodCompra = Integer.parseInt(TxtCod.getText());
            TxtDescricao.setText(JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 1).toString());
            //Faz a troca . por ,
            ValorConvertido = (JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 2).toString());
            ValorConvertido = ValorConvertido.replaceAll("\\.", ",");
            TxtValor.setText(ValorConvertido);

            DataISO = JTDespesa.getValueAt(JTDespesa.getSelectedRow(), 3).toString();
            ConverterDataISO();
            TxtDataInicial.setText(DataConvertidaBR);

            //Preenche a tabela itens
            MostrarItens();
            
        }
    }//GEN-LAST:event_JTDespesaMouseClicked

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        SalvarCompra();        // TODO add your handling code here:
    }//GEN-LAST:event_SalvarActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void PainelItensCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PainelItensCompraMouseClicked

    }//GEN-LAST:event_PainelItensCompraMouseClicked

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
            java.util.logging.Logger.getLogger(TelaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCompra().setVisible(true);
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
    private javax.swing.JLabel LbValorTotalItem1;
    private javax.swing.JLabel LbValorTotalItem2;
    private javax.swing.JLabel LbValorTotalItem3;
    private javax.swing.JLabel LbValorTotalItem4;
    private javax.swing.JLabel LbValorTotalItem5;
    private javax.swing.JPanel PainelCompras;
    private javax.swing.JPanel PainelItensCompra;
    private javax.swing.JButton Salvar;
    private javax.swing.JButton Salvar1;
    private javax.swing.JTextField TxtCod;
    private javax.swing.JTextField TxtCodDinheiro;
    private javax.swing.JFormattedTextField TxtDataInicial;
    private javax.swing.JTextField TxtDescItem;
    private javax.swing.JTextField TxtDescItem1;
    private javax.swing.JTextField TxtDescItem2;
    private javax.swing.JTextField TxtDescItem3;
    private javax.swing.JTextField TxtDescItem4;
    private javax.swing.JTextField TxtDescItem5;
    private javax.swing.JTextField TxtDescricao;
    private javax.swing.JTextField TxtPesquisa;
    private javax.swing.JTextField TxtQuantidadeItem;
    private javax.swing.JTextField TxtQuantidadeItem1;
    private javax.swing.JTextField TxtQuantidadeItem2;
    private javax.swing.JTextField TxtQuantidadeItem3;
    private javax.swing.JTextField TxtQuantidadeItem4;
    private javax.swing.JTextField TxtQuantidadeItem5;
    private javax.swing.JTextField TxtValor;
    private javax.swing.JTextField TxtValorItem;
    private javax.swing.JTextField TxtValorItem1;
    private javax.swing.JTextField TxtValorItem2;
    private javax.swing.JTextField TxtValorItem3;
    private javax.swing.JTextField TxtValorItem4;
    private javax.swing.JTextField TxtValorItem5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
