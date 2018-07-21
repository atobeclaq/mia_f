//
//  FirstViewController.swift
//  ioo
//
//  Created by li chen on 2018/7/17.
//  Copyright © 2018年 miasoft. All rights reserved.
//

import UIKit
var list = ["buy milk","run 5 miles","get peter","plant my new plant"]
class FirstViewController: UIViewController ,UITableViewDelegate,UITableViewDataSource{


	@IBOutlet weak var myTableView: UITableView!
	
	public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int
	{
		return (list.count)
	}
	
	
	public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell
	{
		let cell = UITableViewCell(style:UITableViewCellStyle.default, reuseIdentifier:"cell")
		cell.textLabel?.text = list[indexPath.row] //goes all the items in the list
		return (cell)
	}
	
	func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath){
		if editingStyle == UITableViewCellEditingStyle.delete{
			list.remove(at: indexPath.row)
			myTableView.reloadData()
		}
	}
	override func viewDidAppear(_ animated: Bool) {
		myTableView.reloadData()
	}

	override func viewDidLoad() {
		super.viewDidLoad()
		// Do any additional setup after loading the view, typically from a nib.
	}

	override func didReceiveMemoryWarning() {
		super.didReceiveMemoryWarning()
		// Dispose of any resources that can be recreated.
	}


}

