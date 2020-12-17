function isValidDate(s) {
    if ( ! /^\d{4}\/\d{1,2}\/\d{1,2}$/.test(s) ) {
        return false;
    }
    const parts = s.split('/').map((p) => parseInt(p, 10));
    parts[1] -= 1;
    const d = new Date(parts[0], parts[1], parts[2]);
    return d.getFullYear() === parts[0] && d.getMonth() === parts[1] && d.getDate() === parts[2];
}
