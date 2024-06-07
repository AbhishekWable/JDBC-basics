import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/myjdbc";
        String username="root";
        String password="Abhi@0712";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);

            Statement statement= connection.createStatement();

            //add recode to table
            String sql="insert into student(id,name,address) values(?,?,?)";
            PreparedStatement pst=statement.getConnection().prepareStatement(sql);
            pst.setInt(1,4);
            pst.setString(2,"Shubham");
            pst.setString(3,"Pimpri");
            int status=pst.executeUpdate();
            if(status==1){
                System.out.println("recode updated..");
            }else{
                System.out.println("error");
            }



            //show all records from table
            ResultSet resultSet= statement.executeQuery("select * from student");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1)+" "+ resultSet.getString(2)+ " "+ resultSet.getString(3));
            }
            //update record from table

            String update="update student set address=? where id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(update);
            preparedStatement.setString(1,"kopargaon");
            preparedStatement.setInt(2,1);
            int updateStatus= preparedStatement.executeUpdate();
            if(updateStatus==1){
                System.out.println("data updated");
            }else{
                System.out.println("update error");
            }


            //delete record from table

            String deleteSql = "delete from Student where name = ?";
            PreparedStatement deletePst = connection.prepareStatement(deleteSql);
            deletePst.setString(1, "Shubham");
            int deleteStatus = deletePst.executeUpdate();
            if (deleteStatus == 1) {
                System.out.println("Record deleted.");
            } else {
                System.out.println("Delete error.");
            }

            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}