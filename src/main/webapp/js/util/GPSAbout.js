function parseCourse(icourse)
{  
		if ( (icourse == 0) || (icourse == 360)) {
		  return "向北";
		}
		if ( (icourse > 0) && (icourse <= 30)) {
		  return "北偏东";
		}
		if ( (icourse > 30) && (icourse <= 60)) {
		  return "东北";
		}
		if ( (icourse > 60) && (icourse < 90)) {
		  return "东偏北";
		}
		if (icourse == 90) {
		  return "向东";
		}
		if ( (icourse > 90) && (icourse <= 120)) {
		  return "东偏南";
		}
		if ( (icourse > 120) && (icourse <= 150)) {
		  return  "东南";
		}
		if ( (icourse > 150) && (icourse < 180)) {
		  return "南偏东";
		}
		if (icourse == 180) {
		 return "向南";
		}
		if ( (icourse > 180) && (icourse <= 210)) {
		  return "南偏西";
		}
		if ( (icourse > 210) && (icourse <= 240)) {
		  return "西南";
		}
		 if ( (icourse > 240) && (icourse < 270)) {
		  return "西偏南";
		}
		if (icourse == 270) {
		  return "向西";
		}
		if ( (icourse > 270) && (icourse <= 300)) {
		  return "西偏北";
		}
		if ( (icourse > 300) && (icourse <= 330)) {
		 return "西北";
		}
		if ( (icourse > 330) && (icourse < 360)) {
		  return "北偏西";
		}
		return "";
}