var FPX = {
		lessThanDays:function(variable,days)
		{
			var currentDate = new Date(Date.now());
			currentDate.setDate(currentDate.getDate() - days);
			return (variable < currentDate);
		},
		moreThanDays:function(variable,days)
		{
			var currentDate = new Date(Date.now());
			currentDate.setDate(currentDate.getDate() - days);
			return (variable > currentDate);
		}
};