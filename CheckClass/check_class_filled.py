from urllib import request
from urllib.request import urlopen
from bs4 import BeautifulSoup
import re
from datetime import datetime

id_regex = "(\d{4})|(\d{5})"
course_regex = "(\d{4})|([1-9]\d{2})"
section_regex = "0\d{2}"

course_url = 'http://www.acs.utah.edu/uofu/stu/scheduling/crse-info?term=1158&subj=HONOR&catno=2102'

def meeting_times(course_url):
	"""A give course_url corresponds to a webpage of courses with the same number
	and differing sections and meeting times. This returns that list of 
	UniversityClass's."""
	html = urlopen(course_url).read()
	soup = BeautifulSoup(html, 'html.parser')

	class_list = []
	for class_info in soup.find_all('tr'):
		data = check_if_course_data(list(class_info.descendants))
		if data:
			class_list.append(data)


def check_if_course_data(data_list):
	if not len(data_list) == 7
		return None

	if 








	number = course_id = total = occupied = free = ''
	count = 0
	categories = ['total', 'occupied', 'free']
	class_list = []
	#by experiment, we have found all course information lies in <td> tags
	for item in soup.find_all('td'):
		test = item.getText()
		#experimentally, if it has an align & font fields, and matches the regex, it's a course_id
		if item.get('align') and re.match(id_regex, text) and item.findChildren():
			course_id = text
		else if item.find('a'):
			department = text
		else if re.match(course_regex, text):
			number = text
		else if re.match(section_regex, text):
			section = text
		continue

		if categories[count] == 0:
			eval('total = text')
		else if categories[count] == \eval('occupied = text')
		else if categories[count] == 2
			eval('free = text')
		count += 1
		continue
		class_list.append(UniversityClass(course_id, department, number, section, total, occupied, free))
	return class_list



class UniversityCourse:
	"""Represents a university course, which consists of a department abbreviation,
	e.g. HONOR or MATH or PHYS, and a number, e.g. 1220."""
	def __init__(self, department, number):
		self.course_id = course_id
		self.department = department
		self.number = number
class UniversityClass(UniversityCourse):
	"""Represents a university class which has a section number, and meeting time(s),
	and a time-dynamic total/occupied/free seat count."""
	def __init__(self, course_id, department, number, section, total, occupied, free, time):
		UniversityCourse(self, department, number)
		self.section = section
		self.total = total
		self.occupied = occupied
		self.free = free
		self.time = time

	













