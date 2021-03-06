ContractFinder.directive('datetimepicker', function () {
    return {
        require: '?ngModel',
        link: function (scope, el, attr, ngModel) {
            $(el).datetimepicker({
                todayBtn: "linked",
                todayHighlight: true,
                format: "dd-mm-yyyy hh:ii:ss",
                language: 'pl'
            });
            if (!ngModel) {
                console.log('no model, returning');
                return;
            }

            el.bind('blur keyup change', function() {
                scope.$apply(read);
            });

            read();

            function read() {
                ngModel.$setViewValue(el.val());
            }
        }
    };
});