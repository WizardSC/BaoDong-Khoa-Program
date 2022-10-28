/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChuongTrinh;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author wizardsc
 */
public class GiaoDien extends javax.swing.JFrame {

    ArrayList<String> listTrai = new ArrayList<>();
    ArrayList<String> listPhai = new ArrayList<>();

    public GiaoDien() {
        setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);

    }

    public String TimBaoDong(String baoDong, ArrayList<String> listTrai, ArrayList<String> listPhai) {
        int doDaiBaoDong = 0;
        while (doDaiBaoDong != baoDong.length()) {
            doDaiBaoDong = baoDong.length();
            System.out.println(doDaiBaoDong);
            for (int i = 0; i < listTrai.size(); i++) {
                if (SoSanhChuoi(listTrai.get(i), baoDong) == true) {
                    if (SoSanhChuoi(listPhai.get(i), baoDong) == false) {
                        baoDong += listPhai.get(i);
                    }
                }
            }
        }
        return baoDong;
    }

    private boolean SoSanhChuoi(String chuoiCon, String chuoiCha) {
        int ChuoiCon = 0;
        if ((chuoiCha.length() < chuoiCon.length())) {
            return false;
        }
        for (int i = 0; (i < chuoiCon.length()); i++) {
            for (int j = 0; (j < chuoiCha.length()); j++) {
                if (chuoiCon.charAt(i) == chuoiCha.charAt(j)) {
                    ChuoiCon++;
                    break;
                }

            }
        }
        if ((ChuoiCon == chuoiCon.length())) {
            return true;
        }
        return false;
    }

    //lấy tập L (chỉ xuất hiện vế trái, ko xh vế phải)
    
    public String TimVeTrai(String tapThuocTinh, ArrayList<String> listTrai, ArrayList<String> listPhai) {
        String L = "";
        for (int i = 0; i < tapThuocTinh.length(); i++) {
            for (int t = 0; t < listTrai.size(); t++) {
                if ((SoSanhChuoi(tapThuocTinh.charAt(i) + "", listTrai.get(t))
                        && !SoSanhChuoi(tapThuocTinh.charAt(i) + "", listPhai.get(t)))) {
                    L = L + tapThuocTinh.charAt(i);
                    break;
                }
            }
        }
        return L;

    }

    //lấy tập L (chỉ xuất hiện vế trái, ko xh vế phải)
    
    public String TimVePhai(String tapThuocTinh, ArrayList<String> listTrai, ArrayList<String> listPhai) {
        String R = "";
        for (int i = 0; i < tapThuocTinh.length(); i++) {
            for (int t = 0; (t < listTrai.size()); t++) {
                if ((SoSanhChuoi(tapThuocTinh.charAt(i) + "", listPhai.get(t))
                        && !SoSanhChuoi(tapThuocTinh.charAt(i) + "", listTrai.get(t)))) {
                    R = R + tapThuocTinh.charAt(i);
                    break;
                }
            }
        }
        return R;
    }

    /*lấy TN thuộc tính chỉ xuất hiện ở vế trái, không xuất hiện ở vế phải và
             * các thuộc tính không xuất hiện ở cả vế trái và vế phải của F*/
    
    public String TapNguon(String tapThuocTinh) {
        String R = TimVePhai(tapThuocTinh, listTrai, listPhai);
        System.out.println(R);
        String TN = "";
        for (int i = 0; (i < tapThuocTinh.length()); i++) {
            if (!SoSanhChuoi(tapThuocTinh.charAt(i) + "", R)) {
                TN = TN + tapThuocTinh.charAt(i);
            }

        }

        return TN;
    }

    //lấy TG (giao giữa 2 tập L và R)
    
    
    public String TapTrungGian(String tapThuocTinh) {
        String L = TimVeTrai(tapThuocTinh, listTrai, listPhai);
        String R = TimVePhai(tapThuocTinh, listTrai, listPhai);
        String TG = "";
        for (int i = 0; (i < L.length()); i++) {
            if (SoSanhChuoi(L.charAt(i) + "", R)) {
                TG = TG + L.charAt(i);
            }

        }

        return TG;
    }

    //    Tìm tập con của tập trung gian
    
    private ArrayList<String> TimTapConofTapTrungGian(String TG) {
        ArrayList<String> TapCon = new ArrayList<String>();
        int[] a = new int[TG.length()];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;
        }

        int t = (TG.length() - 1);
        TapCon.add("");
        while ((t >= 0)) {
            t = (TG.length() - 1);
            while (((t >= 0)
                    && (a[t] == 1))) {
                t--;
            }

            if ((t >= 0)) {
                a[t] = 1;
                for (int i = (t + 1); (i < TG.length()); i++) {
                    a[i] = 0;
                }

                String temp = "";
                for (int i = 0; (i < TG.length()); i++) {
                    if ((a[i] == 1)) {
                        temp += TG.charAt(i);
                    }

                }

                TapCon.add(temp);
            }

        }

        return TapCon;
    }

    public ArrayList<String> TimKhoa(String tapThuocTinh, ArrayList<String> listTrai, ArrayList<String> listPhai) {
        ArrayList<String> listKhoa = new ArrayList<String>();
            
        String TN = TapNguon(tapThuocTinh);
        String TG = TapTrungGian(tapThuocTinh);

        //nếu tập TG rỗng thì khóa chính là TN
        if ((TG == "")) {
            listKhoa.add(TN);
            return listKhoa;
        } else {
            ArrayList<String> TapConTG = new ArrayList<String>();
            ///sinh tập con của TG
            TapConTG = TimTapConofTapTrungGian(TG);
            ArrayList<String> SieuKhoa = new ArrayList<String>();
            //kiểm tra từng tập con của TG hợp với TN có là siêu khóa không
            for (int n = 0; n < TapConTG.size(); n++) {
                //lấy giao tập nguồn(TN) với từng con của TG 
                String temp = TN + TapConTG.get(n);
                //nếu giao tập nguồn(TN) và từng con của TG tất cả lấy bao đóng mà bằng tập thuộc tính thì là siêu khóa
                if (SoSanhChuoi(tapThuocTinh, TimBaoDong(temp, listTrai, listPhai))) {
                    SieuKhoa.add(temp);
                }

            }

            //tìm siêu khóa tối thiểu
            for (int i = 0; (i < SieuKhoa.size()); i++) {
                for (int j = i + 1; j < SieuKhoa.size(); j++) {
                    if (SoSanhChuoi(SieuKhoa.get(i), SieuKhoa.get(j))) {
                        SieuKhoa.remove(SieuKhoa.get(j));
                        j--;
                    }

                }

            }

            listKhoa = SieuKhoa;
        }

        return listKhoa;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        btnMinus = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblVeTrai = new javax.swing.JLabel();
        lblVePhai = new javax.swing.JLabel();
        txtPhai = new javax.swing.JTextField();
        txtTapThuocTinh = new javax.swing.JTextField();
        lblTapThuocTinh = new javax.swing.JLabel();
        txtTrai = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtKetQuaKhoa = new javax.swing.JTextField();
        lblKetQua = new javax.swing.JLabel();
        btnTimKhoa = new javax.swing.JButton();
        btnTimThongTin = new javax.swing.JButton();
        lblTapConofTapTG = new javax.swing.JLabel();
        txtTapTrungGian = new javax.swing.JTextField();
        txtTapNguon = new javax.swing.JTextField();
        lblTapTrungGian = new javax.swing.JLabel();
        lblTapNguon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTapConofTapTG = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        lblBaoDong1 = new javax.swing.JLabel();
        txtBaoDong = new javax.swing.JTextField();
        txtKetQuaBaoDong = new javax.swing.JTextField();
        lblKetQua1 = new javax.swing.JLabel();
        btnTimBaoDong = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(219, 226, 239));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(17, 45, 78));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(249, 247, 247));
        jLabel1.setFont(new java.awt.Font("Baloo 2 SemiBold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(249, 247, 247));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHƯƠNG TRÌNH TÌM BAO ĐÓNG + TÌM KHÓA ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 5, 910, 80));

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/close.png"))); // NOI18N
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });
        jPanel2.add(btnClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(874, 8, -1, -1));

        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minus.png"))); // NOI18N
        btnMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinusMouseClicked(evt);
            }
        });
        jPanel2.add(btnMinus, new org.netbeans.lib.awtextra.AbsoluteConstraints(824, 8, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 90));

        jPanel3.setBackground(new java.awt.Color(219, 226, 239));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Nhập thông tin cần thiết", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Baloo 2 ExtraBold", 1, 18), new java.awt.Color(255, 51, 51))); // NOI18N

        lblVeTrai.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblVeTrai.setText("Nhập vế trái của PTH");

        lblVePhai.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblVePhai.setText("Nhập vế phải của PTH");

        txtPhai.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N
        txtPhai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhaiActionPerformed(evt);
            }
        });

        txtTapThuocTinh.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N

        lblTapThuocTinh.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblTapThuocTinh.setText("Nhập tập thuộc tính");

        txtTrai.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N

        btnThem.setFont(new java.awt.Font("Baloo 2", 1, 15)); // NOI18N
        btnThem.setText("Thêm phụ thuộc hàm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnReset.setText("Nhập lại");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTapThuocTinh)
                    .addComponent(lblVeTrai)
                    .addComponent(lblVePhai))
                .addGap(89, 89, 89)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTapThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(lblVePhai)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTapThuocTinh)
                        .addComponent(txtTapThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVeTrai))
                        .addGap(20, 20, 20)
                        .addComponent(txtPhai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 750, 210));

        jPanel4.setBackground(new java.awt.Color(219, 226, 239));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Chức năng tìm khóa & tìm tất cả khóa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Baloo 2 ExtraBold", 1, 18), new java.awt.Color(255, 51, 51))); // NOI18N

        txtKetQuaKhoa.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N

        lblKetQua.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblKetQua.setForeground(new java.awt.Color(255, 0, 0));
        lblKetQua.setText("Kết quả");

        btnTimKhoa.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnTimKhoa.setText("Tìm khóa");
        btnTimKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKhoaActionPerformed(evt);
            }
        });

        btnTimThongTin.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnTimThongTin.setText("Tìm");
        btnTimThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimThongTinActionPerformed(evt);
            }
        });

        lblTapConofTapTG.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblTapConofTapTG.setText("Tập con của tập trung gian");

        txtTapTrungGian.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N
        txtTapTrungGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTapTrungGianActionPerformed(evt);
            }
        });

        txtTapNguon.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N

        lblTapTrungGian.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblTapTrungGian.setText("Tập trung gian");

        lblTapNguon.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblTapNguon.setText("Tập nguồn");

        txtTapConofTapTG.setColumns(20);
        txtTapConofTapTG.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N
        txtTapConofTapTG.setRows(5);
        jScrollPane1.setViewportView(txtTapConofTapTG);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTapConofTapTG)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTapNguon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTapTrungGian, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(32, 32, 32)
                                        .addComponent(btnTimThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblTapTrungGian)
                            .addComponent(lblTapNguon))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKetQuaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblKetQua, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTimKhoa)
                        .addGap(38, 38, 38))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTapNguon)
                            .addComponent(txtTapNguon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTapTrungGian)
                            .addComponent(txtTapTrungGian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnTimThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblTapConofTapTG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblKetQua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTimKhoa)
                    .addComponent(txtKetQuaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 520, 300));

        jPanel5.setBackground(new java.awt.Color(219, 226, 239));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Chức năng tìm bao đóng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Baloo 2 ExtraBold", 1, 18), new java.awt.Color(255, 51, 51))); // NOI18N

        lblBaoDong1.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblBaoDong1.setText("Nhập bao đóng");

        txtBaoDong.setFont(new java.awt.Font("Baloo 2 ExtraBold", 0, 13)); // NOI18N

        txtKetQuaBaoDong.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N

        lblKetQua1.setFont(new java.awt.Font("Baloo 2", 1, 14)); // NOI18N
        lblKetQua1.setForeground(new java.awt.Color(255, 0, 0));
        lblKetQua1.setText("Kết quả");

        btnTimBaoDong.setFont(new java.awt.Font("Baloo 2", 1, 18)); // NOI18N
        btnTimBaoDong.setText("Tìm bao đóng");
        btnTimBaoDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimBaoDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKetQua1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(lblBaoDong1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtKetQuaBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(btnTimBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBaoDong1)
                    .addComponent(txtBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblKetQua1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKetQuaBaoDong, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 380, 300));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String TapThuocTinh = txtTapThuocTinh.getText();
        
        String VeTrai = txtTrai.getText();
        String VePhai = txtPhai.getText();
        
        if(TapThuocTinh.equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tập thuộc tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if(VeTrai.equals("") || VePhai.equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin của PTH", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        listTrai.add(VeTrai);
        listPhai.add(VePhai);

        txtTrai.setText("");
        txtPhai.setText("");
        
        
        System.out.println(listTrai);
        System.out.println(listPhai);
//        jtaDanhSach.setText(VeTrai + "->" + VePhai);

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnTimKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKhoaActionPerformed
        String tapThuocTinh = txtTapThuocTinh.getText();
        ArrayList<String> Khoa = new ArrayList<String>();
        Khoa = TimKhoa(tapThuocTinh, listTrai, listPhai);
        txtKetQuaKhoa.setText(Khoa.toString());
    }//GEN-LAST:event_btnTimKhoaActionPerformed

    private void txtPhaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhaiActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTimThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimThongTinActionPerformed
        String tapThuocTinh = txtTapThuocTinh.getText();
        txtTapNguon.setText(TapNguon(tapThuocTinh));
        txtTapTrungGian.setText(TapTrungGian(tapThuocTinh));

        String TG = TapTrungGian(tapThuocTinh);
        ArrayList<String> TapConTG = new ArrayList<String>();

        TapConTG = TimTapConofTapTrungGian(TG);
        txtTapConofTapTG.setText(TapConTG.toString());
    }//GEN-LAST:event_btnTimThongTinActionPerformed

    private void btnTimBaoDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimBaoDongActionPerformed
        String baoDong = txtBaoDong.getText();
        String KetQua = TimBaoDong(baoDong, listTrai, listPhai);
        txtKetQuaBaoDong.setText(baoDong + "+ = " + KetQua);
    }//GEN-LAST:event_btnTimBaoDongActionPerformed

    private void txtTapTrungGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTapTrungGianActionPerformed

    }//GEN-LAST:event_txtTapTrungGianActionPerformed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnMinusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinusMouseClicked
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btnMinusMouseClicked

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {

        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnMinus;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimBaoDong;
    private javax.swing.JButton btnTimKhoa;
    private javax.swing.JButton btnTimThongTin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBaoDong1;
    private javax.swing.JLabel lblKetQua;
    private javax.swing.JLabel lblKetQua1;
    private javax.swing.JLabel lblTapConofTapTG;
    private javax.swing.JLabel lblTapNguon;
    private javax.swing.JLabel lblTapThuocTinh;
    private javax.swing.JLabel lblTapTrungGian;
    private javax.swing.JLabel lblVePhai;
    private javax.swing.JLabel lblVeTrai;
    private javax.swing.JTextField txtBaoDong;
    private javax.swing.JTextField txtKetQuaBaoDong;
    private javax.swing.JTextField txtKetQuaKhoa;
    private javax.swing.JTextField txtPhai;
    private javax.swing.JTextArea txtTapConofTapTG;
    private javax.swing.JTextField txtTapNguon;
    private javax.swing.JTextField txtTapThuocTinh;
    private javax.swing.JTextField txtTapTrungGian;
    private javax.swing.JTextField txtTrai;
    // End of variables declaration//GEN-END:variables
}
