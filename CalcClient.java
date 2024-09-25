// Client.java
import java.net.*;
import java.io.*;
public class CalcClient{
    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost", 3333);
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String operation, num1, num2, result;

            while(true){
                System.out.println("Enter operation (add/subtract/multiply/divide/square root/cube root/power/natural log/log/sine/cos/tan/stop): ");
                operation = br.readLine();
                if(operation.equals("stop")){
                    dout.writeUTF(operation);
                    break;
                }
                System.out.println("Enter first number: ");
                num1 = br.readLine();
                
                if(operation.equalsIgnoreCase("square root") || operation.equalsIgnoreCase("cube root")|| operation.equalsIgnoreCase("log")|| operation.equalsIgnoreCase("natural log")|| operation.equalsIgnoreCase("sine")|| operation.equalsIgnoreCase("cosine")|| operation.equalsIgnoreCase("tan")) {
                    dout.writeUTF(operation);
                    dout.writeUTF(num1);
                } else {
                    System.out.println("Enter second number: ");
                    num2 = br.readLine();
                    dout.writeUTF(operation);
                    dout.writeUTF(num1);
                    dout.writeUTF(num2);
                }
                dout.flush();
                result = din.readUTF();
                System.out.println("Result: " + result);
            }
            din.close();
            dout.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
}
