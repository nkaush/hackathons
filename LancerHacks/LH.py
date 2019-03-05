from sportsreference.nba.roster import Player
from per import uPER
import re
from teams import nba, team_names
from player_per import all_per, all_per_by_team_list, all_per_by_team_dict, all_per_by_team_dict_top_7

players_list = []
player_id_regex = re.compile(r'''(/players/[a-z]/)(\w+)([0-9]{2})(\.html)''')

def search():
	s = ""
	while s != "done":
		s = input("Enter: ")
		test = player_id_regex.findall(s)
		print(test)
		for i in test:
			pid = str(i[1]+i[2])
			print(pid)
			players_list.append(pid)

def l():
	global players_list
	temp = players_list[:]
	print(players_list)
	players_list = []
	return temp

players_per = {}
players_per_by_team = []
team_avg_per = {}
averages = [14.281818181818183, 16.0875, 13.833333333333334, 12.41875, 11.364285714285714, 15.499999999999998, 13.357142857142858, 13.050000000000002, 13.5, 12.3875, 14.585714285714287, 14.72, 12.200000000000003, 12.273333333333335, 13.479999999999997, 15.319999999999999, 13.194117647058825, 13.77142857142857, 14.256250000000001, 11.859999999999998, 12.41875, 13.214285714285717, 12.921428571428573, 14.243750000000002, 12.920000000000003, 14.528571428571428, 13.949999999999998, 15.206249999999999, 13.657142857142858, 12.117647058823527]
all_teams = nba()
all_team_names = team_names()

players_per_by_team_list = all_per_by_team_list()
players_per_by_team_dict = all_per_by_team_dict()

def main(): 
	global all_teams, averages, players_per
	for team in all_teams:
		total = 0
		temp_dic = {}
		counter = 0
		for player_id in team:
			tp = Player(player_id)
			name = tp.name
			efficiency = tp('2017-18').player_efficiency_rating
			temp_dic[name] = efficiency
			players_per[name] = efficiency
			if efficiency < 32.5 and efficiency > 0:
				total += efficiency
				counter += 1
			print(name, efficiency)
		avg = total/counter
		averages.append(avg)
		players_per_by_team.append(temp_dic)

def file():
	global all_team_names, team_avg_per, averages
	f = open("nba_team_per_updated.txt", "w+")
	for i in range(len(all_team_names)):
		team_avg_per[all_team_names[i]] = averages[i]
		f.write(("%s:%s\n")%(all_team_names[i], averages[i]))
	f.close()

def new_file():
	dic = all_per_by_team_dict_top_7()
	f = open("nba_team_per_updated.txt", "w+")
	teams = list(dic.keys())
	players = dic.values()
	global averages
	for group in players:
		total = 0
		for val in group.values():
			total += val
		total = total/len(group.values())
		averages.append(total)
	for i in range(len(teams)):
		f.write(("%s:%s\n"%(teams[i], averages[i])))
	f.close()

main()
new_file()
