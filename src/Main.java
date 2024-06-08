import java.sql.*;
public class Main {
    public static void main(String[] args) {

        String url="jdbc:mysql://localhost:3306/myjdbc";
        String username="root";
        String password="Abhi@0712";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);

            Statement statement= connection.createStatement();

            String create="create table teacher(id int primary key,name varchar(50),address varchar(100))";
            statement.executeUpdate(create);
            System.out.println("table created successfully..");


            //add recode to table
            String sql="insert into teacher(id,name,address) values(?,?,?)";
            PreparedStatement pst=statement.getConnection().prepareStatement(sql);
            pst.setInt(1,4);
            pst.setString(2,"Dinesh");
            pst.setString(3,"Hinjewadi");
            int status=pst.executeUpdate();
            if(status==1){
                System.out.println("record added..");
            }else{
                System.out.println("error");
            }



            //show all records from table
            String select="select * from teacher";
            PreparedStatement selectPst= connection.prepareStatement(select);
            ResultSet resultSet= statement.executeQuery(select);
            while(resultSet.next()){
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            String address= resultSet.getNString(3);
                System.out.println(id +". " + name + " " + address);

            }
            //update record from table

            String update="update teacher set address=? where id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(update);
            preparedStatement.setString(1,"Pune");
            preparedStatement.setInt(2,2);
            int updateStatus= preparedStatement.executeUpdate();
            if(updateStatus==1){
                System.out.println("data updated");
            }else{
                System.out.println("update error");
            }


            //delete record from table

            String deleteSql = "delete from teacher where name = ?";
            PreparedStatement deletePst = connection.prepareStatement(deleteSql);
            deletePst.setString(1, "Dinesh");
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