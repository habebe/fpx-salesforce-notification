var FPX = {
		moreThanDays:function(variable,days)
		{
			var currentDate = new Date(Date.now());
			currentDate.setDate(currentDate.getDate() - days);
			return (variable < currentDate);
		},
		moreThanHours:function(variable,hours)
		{
			var currentDate = new Date(Date.now());
			currentDate.setHours(currentDate.getHours() - hours);
			return (variable < currentDate);
		}
};