ContractFinder.directive('timeleft', function () {
    return {
        require: '?ngModel',
        link: function (scope, el, attr, ngModel) {
            if (!ngModel) {
                console.log('no model, returning');
                return;
            }
            var contract = scope[attr.ngModel];
            if (contract.weeks < 0 || contract.days < 0 || contract.hours < 0 || contract.minutes < 0 || contract.seconds < 0) {
                $(el).append('Zakończone');
                $(el).css('color', 'red');
            } else {
                var value = contract.weeks > 0 ? contract.weeks + 'tyg. ':'';
                value += contract.days > 0 ? contract.days + 'dni ':'';
                value += contract.hours > 0 ? contract.hours + 'godz. ':'';
                value += contract.minutes > 0 ? contract.minutes + 'min. ':'';
                //value += contract.seconds > 0 ? contract.seconds + 'sec.':'';
                $(el).append(value);
                if (contract.weeks <=0 && contract.days <= 0 && contract.hours <= 0 && contract.minutes < 60 && contract.minutes >= 10) {
                    $(el).css('color', 'orange');
                } else if (contract.weeks <=0 && contract.days <= 0 && contract.hours <= 0 && contract.minutes < 10) {
                    $(el).css('color', 'red');
                } else {
                    $(el).css('color', 'green');
                }
            }
        }
    };
});