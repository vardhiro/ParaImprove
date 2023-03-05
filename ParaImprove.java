import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

class ParaImprove
{
    static String user = "", response = "";
    public static String improve(String u) throws Exception
    {
        String command = " python request.py \"Improve this paragraph: `" + u + "`\" ", res = "";
        System.out.println(command);
        Process child = Runtime.getRuntime().exec(command);

        InputStream in = child.getInputStream();
        int c;
        while ((c = in.read()) != -1) {
            res += (char) c;
        }
        in.close();
        return res;
    }
    public static void main(String[] args) {
        // Frame init 
        JFrame f = new JFrame("ParaImprover");
        f.setSize(900, 700);
        f.setBackground(Color.BLACK);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        // Top menubar 
        JMenuBar mb = new JMenuBar();
        f.add(BorderLayout.NORTH,mb);
        JLabel l = new JLabel("Improve your text with ParaImprove");
        Font lf = new Font("Calibri", 0, 20);
        l.setFont(lf);
        mb.add(l);

        // User TextArea
        JTextArea ut = new JTextArea("Your Text here...",5,20); 
        ut.setFont(lf);
        JScrollPane scroll = new JScrollPane(ut);
        scroll.setSize(600, 300);
        ut.setLineWrap(true);
        ut.setWrapStyleWord(true);
        f.add(BorderLayout.WEST,scroll);   

        // Panel with buttons
        JPanel p = new JPanel();
        JButton go = new JButton("Improve");
        JButton re = new JButton("Reset");
        p.add(go);
        p.add(re);
        f.add(p);

        // GPT textarea
        JTextArea gt = new JTextArea("Improved text to appear here after \"Improve\" button is clicked...", 5, 20);
        gt.setFont(lf);
        JScrollPane scroll2 = new JScrollPane(gt);
        scroll2.setSize(600, 300);
        gt.setLineWrap(true);
        gt.setWrapStyleWord(true);
        f.add(BorderLayout.EAST, scroll2);

        // reset function set
        re.addActionListener(e -> {
            ut.setText("Your Text here...");
            gt.setText("Improved text to appear here after \"Improve\" button is clicked...");
        });

        // Improve function set
        go.addActionListener(e -> {
            try{
                user = ut.getText();
                response = improve(user);
                // System.out.println(response);
                gt.setText(response);
            }catch(Exception exception)
            {
                System.exit(-1);
            }
        });
    }
}