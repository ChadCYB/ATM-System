class TransferFrame extends JFrame implements ActionListener{
		private JPanel contentPane;
		private JLabel Transfer_Account,Money ;
		private JTextField txtTransfer_Account,txtMoney ;
		private JButton Confirm,Cancel;
	
	TransferFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 250);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,2,0,50));
		
		JLabel Transfer_Account=new JLabel("輸入轉帳帳號");   //輸入轉帳帳號文字(顯示)
		contentPane.add(Transfer_Account);
		
		JTextField txtTransfer_Account =new JTextField();     //輸入金額 (輸入區)
		contentPane.add(txtTransfer_Account);
		
		JLabel Money=new JLabel("輸入金額");                  //輸金額號文字(顯示)
		contentPane.add(Money);
		
		JTextField txtMoney =new JTextField();                //輸入金額 (輸入區)
		contentPane.add(txtMoney);
		
		JButton Confirm=new JButton("Confirm");               //確認按鈕
		contentPane.add(Confirm);
		Confirm.addActionListener(this);                      //放入請聽
		
		JButton Cancel=new JButton("Cancel");                 //取消按鈕
		contentPane.add(Cancel);
		Cancel.addActionListener(this);                       //放入請聽
		
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e){	            //測試區
		JButton HitBtn=(JButton)e.getSource();                //看是誰被啟動的
		JOptionPane.showMessageDialog(null, HitBtn.getText());

	}
}
