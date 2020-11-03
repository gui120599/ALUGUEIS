/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Dao.CondicoesPagamentosDao;
import Dao.ItensEntradaDao;
import Dao.TiposPagamentosDao;
import Dao.EntradasDao;
import Modelo.CondicoesPagamentos;
import Modelo.ItensEntrada;
import Modelo.TiposPagamentos;
import Modelo.Entradas;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Suporte T.I 2
 */
public class TelaEntradas extends javax.swing.JFrame {

    /**
     * Creates new form TelaCliente
     */
    String CustoConvertido, VendaConvertido, LucroConvertido;
    Double LucroItem, TotalItem;
    int quantidade;
    int Cod_item;

    public TelaEntradas() {
        initComponents();
        // MostrarCliente();
        TxtDescricaoVenda.requestFocus();
        PreencherComboTipoPagamento();
        PreencherComboCondicaoPagamento();
        AbrirVenda();
    }

    public void AbrirVenda() {
        EntradasDao idao = new EntradasDao();
        idao.AbrirVenda(0);
        idao.BuscarUltimaVenda().forEach((c) -> {
            TxtCodVenda.setText(Integer.toString(c.getCod_venda()));
        });
    }

    //carrega Tabela
    public void MostrarItens() {
        JTItensEntrada.getColumnModel().getColumn(0).setPreferredWidth(60);
        JTItensEntrada.getColumnModel().getColumn(1).setPreferredWidth(100);
        JTItensEntrada.getColumnModel().getColumn(2).setPreferredWidth(70);
        DefaultTableModel modelo = (DefaultTableModel) JTItensEntrada.getModel();
        modelo.setNumRows(0);
        ItensEntradaDao cdao = new ItensEntradaDao();

        cdao.BuscarItens(Integer.parseInt(TxtCodVenda.getText())).forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getCod_item(),
                c.getDescricao_item(),
                c.getQuantidade(),
                c.getValor_venda()
            });
        });
    }

    //carrega Tabela
    public void MostrarValores() {

        ItensEntradaDao cdao = new ItensEntradaDao();

        cdao.BuscarItens(Integer.parseInt(TxtCodVenda.getText())).forEach((c) -> {
            LbLucro.setText(Double.toString(c.getValor_lucro()));
            LbCusto.setText(Double.toString(c.getValor_custo()));
            LbTotal.setText(Double.toString(c.getValor_total()));

        });
    }

    public void PreencherComboTipoPagamento() {
        TiposPagamentosDao udao = new TiposPagamentosDao();
        udao.BuscarTipoPagamento().forEach((u) -> {
            ComboBoxTipoPagamento.addItem(u);
        });
    }

    public void PreencherComboCondicaoPagamento() {
        CondicoesPagamentosDao udao = new CondicoesPagamentosDao();
        udao.BuscarCondicao().forEach((u) -> {
            ComboBoxCondicaoPagamento.addItem(u);
        });
    }

    //Limpar Campos
    public void LimparCampos() {
        TxtCodVenda.setText("");
        TxtCodCliente.setText("");
        TxtNomeCliente.setText("");
        TxtCodTipoPagamento.setText("");
        TxtCodCondicaoPagamento.setText("");
    }

    public void LimparCamposItens() {
        TxtDescItem.setText("");
        TxtValorCustoItem.setText("");
        TxtValorVendaItem.setText("");
        TxtValorLucroItem.setText("");
        TxtQuantidadeItem.setText("");
        LbValorTotalItem.setText("");
    }

    //Salvar Item da Venda
    public void SalvarItemVenda() {
        ItensEntrada i = new ItensEntrada();
        ItensEntradaDao idao = new ItensEntradaDao();
        if (TxtDescItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Descrição do item é obrigatório!");
            TxtDescItem.requestFocus();
        } else if (TxtValorCustoItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Valor custo do item é obrigatório!");
            TxtValorCustoItem.requestFocus();
        } else if (TxtValorVendaItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Valor de Venda do item é obrigatório!");
            TxtValorVendaItem.requestFocus();
        } else if (TxtQuantidadeItem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Quantidade do item é obrigatório!");
            TxtQuantidadeItem.requestFocus();
        }

        i.setDescricao_item(TxtDescItem.getText());
        i.setCod_venda(Integer.parseInt(TxtCodVenda.getText()));
        i.setQuantidade(Integer.parseInt(TxtQuantidadeItem.getText()));
        i.setValor_custo(Double.parseDouble(CustoConvertido));
        i.setValor_venda(Double.parseDouble(VendaConvertido));
        i.setValor_lucro(Double.parseDouble(TxtValorLucroItem.getText()));
        i.setValor_total(Double.parseDouble(LbValorTotalItem.getText()));
        idao.SalvarItem(i);
        LimparCamposItens();
        MostrarItens();
        MostrarValores();
        TxtDescItem.requestFocus();
    }

    //Remover Item
    public void RemoverItem() {
        Cod_item = Integer.parseInt(JTItensEntrada.getValueAt(JTItensEntrada.getSelectedRow(), 0).toString());

        if (Cod_item == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um Item!!");

        } else {
            ItensEntradaDao idao = new ItensEntradaDao();
            idao.RemoverItensEntrada(Cod_item);
            MostrarItens();
        }
    }

    //Salvar Venda
    public void SalvarVenda() {
        Entradas v = new Entradas();
        EntradasDao vdao = new EntradasDao();

        v.setCod_cliente(Integer.parseInt(TxtCodCliente.getText()));
        v.setCod_condicao_pagamento(Integer.parseInt(TxtCodCondicaoPagamento.getText()));
        v.setCod_tipo_pagamento(Integer.parseInt(TxtCodTipoPagamento.getText()));
        v.setValor_desconto(Double.parseDouble(TxtDesconto.getText()));
        v.setValor_custo(Double.parseDouble(LbCusto.getText()));
        v.setValor_lucro(Double.parseDouble(LbLucro.getText()));
        v.setValor_venda(Double.parseDouble(LbTotal.getText()));
        vdao.SalvarEntrada(v);
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
        TxtCodVenda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtDescricaoVenda = new javax.swing.JTextField();
        LbCliente = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Salvar = new javax.swing.JButton();
        Salvar1 = new javax.swing.JButton();
        TxtNomeCliente = new javax.swing.JLabel();
        TxtCodCliente = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ComboBoxTipoPagamento = new javax.swing.JComboBox<>();
        TxtCodTipoPagamento = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TxtCodCondicaoPagamento = new javax.swing.JLabel();
        ComboBoxCondicaoPagamento = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TxtDescItem = new javax.swing.JTextField();
        TxtValorCustoItem = new javax.swing.JTextField();
        TxtValorVendaItem = new javax.swing.JTextField();
        TxtQuantidadeItem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        TxtLucro = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TxtValorLucroItem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        LbValorTotalItem = new javax.swing.JLabel();
        jBRemoverItem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTItensEntrada = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        LbTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LbCusto = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        LbLucro = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TxtDesconto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Entradas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(335, 335, 335)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel2.setText("Código Venda");

        TxtCodVenda.setEditable(false);
        TxtCodVenda.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel3.setText("Descrição Venda");

        TxtDescricaoVenda.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        LbCliente.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        LbCliente.setText("Cliente");
        LbCliente.setToolTipText("Selecionar Cliente");
        LbCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LbCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LbClienteMouseClicked(evt);
            }
        });

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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        TxtNomeCliente.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        TxtNomeCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        TxtCodCliente.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        TxtCodCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        jLabel5.setText("Tipo Pagamento");

        ComboBoxTipoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxTipoPagamentoActionPerformed(evt);
            }
        });

        TxtCodTipoPagamento.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        TxtCodTipoPagamento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        jLabel6.setText("Condição de Pagamento");

        TxtCodCondicaoPagamento.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        TxtCodCondicaoPagamento.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        ComboBoxCondicaoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxCondicaoPagamentoActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Itens da venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 2, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel7.setText("Descrição");

        jLabel8.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel8.setText("Valor custo");

        jLabel9.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel9.setText("Valor Venda");

        jLabel10.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel10.setText("Quantidade");

        TxtValorVendaItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtValorVendaItemKeyReleased(evt);
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

        TxtLucro.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        TxtLucro.setForeground(new java.awt.Color(0, 0, 204));

        jLabel14.setFont(new java.awt.Font("Calibri", 2, 11)); // NOI18N
        jLabel14.setText("Valor Lucro");

        TxtValorLucroItem.setEditable(false);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel14))
                        .addGap(22, 22, 22)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtDescItem)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(TxtValorLucroItem, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(TxtQuantidadeItem))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(TxtValorCustoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(TxtValorVendaItem, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LbValorTotalItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(3, 3, 3)
                        .addComponent(jBRemoverItem)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TxtDescItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(TxtValorCustoItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(TxtValorVendaItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(TxtValorLucroItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtQuantidadeItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TxtLucro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBRemoverItem))
                    .addComponent(jLabel16)
                    .addComponent(LbValorTotalItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTItensEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Qtd", "Valor Venda"
            }
        ));
        JTItensEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JTItensEntrada.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(JTItensEntrada);

        jLabel11.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel11.setText("VALOR TOTAL");

        LbTotal.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        LbTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel13.setText("VALOR CUSTO");

        LbCusto.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        LbCusto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel15.setText("VALOR LUCRO");

        LbLucro.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        LbLucro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        jLabel12.setText("Desconto");

        TxtDesconto.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2)
                                        .addGap(4, 4, 4)
                                        .addComponent(TxtCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtDescricaoVenda))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(LbCliente)
                                        .addGap(10, 10, 10)
                                        .addComponent(TxtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(10, 10, 10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addGap(14, 14, 14)
                                .addComponent(TxtCodTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(ComboBoxTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)
                                .addComponent(TxtCodCondicaoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(ComboBoxCondicaoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LbLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LbCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtCodVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtDescricaoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LbCliente)
                                    .addComponent(TxtCodCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TxtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(TxtCodTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBoxTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel6))
                                    .addComponent(TxtCodCondicaoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ComboBoxCondicaoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(LbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(LbCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(LbLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JTClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTClienteMouseClicked

    }//GEN-LAST:event_JTClienteMouseClicked

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        SalvarVenda();
    }//GEN-LAST:event_SalvarActionPerformed

    private void TxtQuantidadeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQuantidadeItemActionPerformed

    }//GEN-LAST:event_TxtQuantidadeItemActionPerformed

    private void ComboBoxTipoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxTipoPagamentoActionPerformed
        TiposPagamentos a = (TiposPagamentos) ComboBoxTipoPagamento.getSelectedItem();
        TxtCodTipoPagamento.setText(Integer.toString(a.getCod_tipo_pagamento()).trim());        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxTipoPagamentoActionPerformed

    private void ComboBoxCondicaoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxCondicaoPagamentoActionPerformed
        CondicoesPagamentos a = (CondicoesPagamentos) ComboBoxCondicaoPagamento.getSelectedItem();
        TxtCodCondicaoPagamento.setText(Integer.toString(a.getCod_condicao()).trim());         // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxCondicaoPagamentoActionPerformed

    private void LbClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LbClienteMouseClicked
        TelaSelecionaCliente t = new TelaSelecionaCliente(this, rootPaneCheckingEnabled);
        t.setVisible(true);
        TxtCodCliente.setText(Integer.toString(t.CodCliente));
        TxtNomeCliente.setText(t.NomeCliente);
    }//GEN-LAST:event_LbClienteMouseClicked

    private void TxtValorVendaItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtValorVendaItemKeyReleased
        //Converte as virgulas em pontos e formata para apenas 2 numeros apos a virgula
        if (TxtValorCustoItem.getText() == null || TxtValorCustoItem.getText().equals("")) {
            TxtValorVendaItem.setText("");
            TxtValorCustoItem.requestFocus();
            JOptionPane.showMessageDialog(this, "Insira primeiro o valor de custo do item!");
        } else {

            CustoConvertido = String.valueOf(TxtValorCustoItem.getText());
            VendaConvertido = String.valueOf(TxtValorVendaItem.getText());
            CustoConvertido = CustoConvertido.replaceAll(",", ".");
            VendaConvertido = VendaConvertido.replaceAll(",", ".");

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            LucroItem = Double.parseDouble(VendaConvertido) - Double.parseDouble(CustoConvertido);

            LucroConvertido = String.valueOf(TxtValorLucroItem.getText());
            TxtValorLucroItem.setText(df.format(LucroItem));
            LucroConvertido = LucroConvertido.replaceAll("\\.", ",");
        }
    }//GEN-LAST:event_TxtValorVendaItemKeyReleased

    private void TxtQuantidadeItemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtQuantidadeItemKeyReleased
        quantidade = Integer.parseInt(TxtQuantidadeItem.getText());
        //Coloca no padrão de reais somente 2 casas após a vírgula
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        TotalItem = (Double.parseDouble(VendaConvertido) * (Integer.parseInt(TxtQuantidadeItem.getText())));
        LbValorTotalItem.setText(Double.toString(TotalItem));
    }//GEN-LAST:event_TxtQuantidadeItemKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SalvarItemVenda();
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
            java.util.logging.Logger.getLogger(TelaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEntradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaEntradas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> ComboBoxCondicaoPagamento;
    private javax.swing.JComboBox<Object> ComboBoxTipoPagamento;
    private javax.swing.JTable JTItensEntrada;
    private javax.swing.JLabel LbCliente;
    private javax.swing.JLabel LbCusto;
    private javax.swing.JLabel LbLucro;
    private javax.swing.JLabel LbTotal;
    private javax.swing.JLabel LbValorTotalItem;
    private javax.swing.JButton Salvar;
    private javax.swing.JButton Salvar1;
    private javax.swing.JLabel TxtCodCliente;
    private javax.swing.JLabel TxtCodCondicaoPagamento;
    private javax.swing.JLabel TxtCodTipoPagamento;
    private javax.swing.JTextField TxtCodVenda;
    private javax.swing.JTextField TxtDescItem;
    private javax.swing.JTextField TxtDesconto;
    private javax.swing.JTextField TxtDescricaoVenda;
    private javax.swing.JLabel TxtLucro;
    private javax.swing.JLabel TxtNomeCliente;
    private javax.swing.JTextField TxtQuantidadeItem;
    private javax.swing.JTextField TxtValorCustoItem;
    private javax.swing.JTextField TxtValorLucroItem;
    private javax.swing.JTextField TxtValorVendaItem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jBRemoverItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
