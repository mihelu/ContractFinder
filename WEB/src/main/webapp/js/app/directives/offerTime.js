ContractFinder.directive('offertime', function () {
    return {
        require: '?ngModel',
        link: function (scope, el, attr, ngModel) {
            if (!ngModel) {
                console.log('no model, returning');
                return;
            }
            var offer = scope[attr.ngModel];
            var value = offer.years > 0 ? offer.years + ' lat ':'';
            value += offer.months > 0 ? offer.months + ' miesięcy ':'';
            value += offer.days > 0 ? offer.days + ' dni ':'';
            value += offer.hours > 0 ? offer.hours + ' godz':'';
            $(el).append(value);
        }
    };
});