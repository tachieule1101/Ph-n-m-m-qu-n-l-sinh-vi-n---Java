/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doanjava;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TCL
 */
public class StudentModify {

    public static List<Student> findAll() {
        List<Student> studentsList = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        try {
            //lay danh sach sinh viên
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager", "root", "");
            //query
            String sql = "SELECT * FROM `student`";
            statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Student std = new Student(resultSet.getString("mssv"),
                        resultSet.getString("hoten"),
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("queQuan"),
                        resultSet.getString("email"),
                        resultSet.getString("NamSinh"),
                        resultSet.getInt("SDT"));
                studentsList.add(std);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc
        return studentsList;
    }

    //Them Sinh Vien
    public static void insert(Student std) {
        Connection conn = null;
//        PreparedStatement statement = null;
        try {
            //lay danh sach sinh viên
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager", "root", "");
            //query
            String sql = "INSERT INTO `student`(`MSSV`, `HoTen`, `NamSinh`, `GioiTinh`, `QueQuan`, `Email`, `SDT`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
//            statement = conn.prepareStatement(sql);

            ps.setString(1, std.getMssv());
            ps.setString(2, std.getHoTen());
            ps.setString(3, std.getNamSinh());
            ps.setString(4, std.getGioiTinh());
            ps.setString(5, std.getQueQuan());
            ps.setString(6, std.getEmail());
            ps.setInt(7, std.getSdt());

            int i = ps.executeUpdate();
            if (i >= 1) {
                System.out.println("them du lieu thanh cong \n");
            }
//            ResultSet resultSet = statement.executeQuery(sql);
//            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Update Sinh Vien
    public static void update(Student std) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //lay danh sach sinh viên
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager", "root", "");
            //query
            String sql = "UPDATE `student` SET `HoTen`='?',`NamSinh`='?',`GioiTinh`='?',`QueQuan`='?',`Email`='?',`SDT`='?' WHERE `MSSV`= '?'";
            statement = conn.prepareStatement(sql);

            statement.setString(1, std.getHoTen());
            statement.setString(2,std.getNamSinh());
            statement.setString(3, std.getGioiTinh());
            statement.setString(4, std.getQueQuan());
            statement.setString(5, std.getEmail());
            statement.setInt(6, std.getSdt());
            statement.setString(7, std.getMssv());

            ResultSet resultSet = statement.executeQuery(sql);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    //Xoa Sinh Vien
    public static void delete(Student std) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //lay danh sach sinh viên
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager", "root", "");
            //query
            String sql = "DELETE FROM `student` WHERE `MSSV`= '?'";
            statement = conn.prepareStatement(sql);

            statement.setString(1, std.getMssv());

            ResultSet resultSet = statement.executeQuery(sql);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //tim kiem
    public static List<Student> findByFullName(String mssv) {
        List<Student> studentsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            //lay danh sach sinh viên
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_manager", "root", "");
            //query
            String sql = "SELECT * FROM `student` where mssv like ?";
            statement = conn.prepareCall(sql);
            statement.setString(1, "%"+mssv+"%");
            
            
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Student std = new Student(resultSet.getString("mssv"),
                        resultSet.getString("hoten"),
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("queQuan"),
                        resultSet.getString("email"),
                        resultSet.getString("NamSinh"),
                        resultSet.getInt("SDT"));
                studentsList.add(std);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //ket thuc
        return studentsList;
    }
}
